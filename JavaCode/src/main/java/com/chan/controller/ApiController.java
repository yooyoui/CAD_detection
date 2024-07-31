package com.chan.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chan.entity.CadContent;
import com.chan.entity.DetInfoDTO;
import com.chan.entity.RequestContent;
import com.chan.entity.ScriptExecResult;
import com.chan.proto.CadDetProto;
import com.chan.service.impl.CadContentServiceImpl;
import com.chan.service.impl.GrpcScriptExecImpl;
import com.chan.utils.ImageSaver;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/CadDet")
@RestController
public class ApiController {
    /**
     * 功能：1. 调用python脚本并返回结果
     * 2. 数据库CRUD操作
     */
    private ScriptExecResult tempResult;

    private GrpcScriptExecImpl grpcScriptExec;

    @Autowired
    public void setGrpcScriptExec(GrpcScriptExecImpl grpcScriptExec) {
        this.grpcScriptExec = grpcScriptExec;
    }

    // 调用py接口
    @PostMapping("CallPy")
    public Object script(@RequestBody Map<String, Object> request) {

        String path = (String) request.getOrDefault("path", "");
        if (path.isEmpty()) {
            return "";
        }

        ScriptExecResult scriptExecResult = this.grpcScriptExec.exec(new RequestContent(path));
        String fileName = scriptExecResult.getFileName();
        List<CadDetProto.DetInfo> result = scriptExecResult.getResult();

        this.tempResult = scriptExecResult;

        return fileName + " " + result;
    }

    // 将数据以表格形式返回
    @PostMapping("/getDataForTable")
    public ResponseEntity<List<DetInfoDTO>> getDataForTable() {

        List<DetInfoDTO> dtos = new ArrayList<>();
        for (CadDetProto.DetInfo detInfo : this.tempResult.getResult()) {
            List<Integer> position = Arrays.asList(
                    detInfo.getPosition().getLeftTop(),
                    detInfo.getPosition().getRightTop(),
                    detInfo.getPosition().getRightBottom(),
                    detInfo.getPosition().getLeftBottom()
            );
            List<String> attribute = new ArrayList<>(detInfo.getAttribute().getValueList());
            dtos.add(new DetInfoDTO(detInfo.getId(), position, attribute));
        }
        return ResponseEntity.ok(dtos);
    }

    // 将结果图片保存到指定路径
    @PostMapping("/getResultImg")
    public Object getResultImg() {

        String fileName = this.tempResult.getFileName();
        byte[] image = this.tempResult.getImage();

        String outputPath = "D:/dev_code/java/projectGrpc/grpc-backend/src/main/resources/static/" + fileName;

        try {
            ImageSaver.saveImage(image, outputPath);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image.", e);
        }

        return ResponseEntity.ok("http://localhost:8080/static/" + fileName);
    }

    @Resource
    private CadContentServiceImpl dataService;

    // 数据库CRUD接口
    @PostMapping("/create")
    public Object create() {

        List<CadDetProto.DetInfo> tempData = this.tempResult.getResult();

        if (tempData != null) {
            for (CadDetProto.DetInfo obj : tempData) {

                int id = obj.getId();
                CadDetProto.Position positions = obj.getPosition();
                CadDetProto.Attribute attributes = obj.getAttribute();

                String position = "[" + positions.getLeftTop() + ","
                        + positions.getRightTop() + ","
                        + positions.getRightBottom() + ","
                        + positions.getLeftBottom() + "]";

                String value = "" + attributes.getValueList();

                dataService.save(new CadContent(position, value, id));
            }
        } else {
            return ResponseEntity.badRequest().body("结果为空或未执行算法");
        }
        return ResponseEntity.ok("数据已存入");
    }

    @GetMapping("/read")
    public Object read() {
        return dataService.list();
    }

    @PutMapping("/update")
    public Object update(@RequestParam Integer id, String position, String value) {

        if (id == null || position == null || position.isEmpty() || value == null || value.isEmpty()) {
            return ResponseEntity.badRequest().body("参数不能为空");
        }
        UpdateWrapper<CadContent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
                .set("position", position)
                .set("value", value);
        boolean isUpdate = dataService.update(updateWrapper);
        if (isUpdate) {
            return ResponseEntity.ok("数据更新成功");
        } else {
            return ResponseEntity.badRequest().body("数据更新失败，请检查id是否存在");
        }
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestParam Integer id) {

        if (id == null) {
            return ResponseEntity.badRequest().body("id参数不能为空");
        }
        boolean isDelete = dataService.removeById(id);
        if (isDelete) {
            return ResponseEntity.ok("数据删除成功");
        } else {
            return ResponseEntity.badRequest().body("数据删除失败");
        }

    }

}
