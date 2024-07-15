package com.chan.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chan.entity.CadContent;
import com.chan.entity.CadData;
import com.chan.service.impl.CadDataServiceImpl;
import com.chan.service.impl.GrpcScriptExecImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RequestMapping("/CadDet")
@RestController
public class ApiController {
    /**
     * 功能：1. 调用python脚本并返回结果
     * 2. 数据库CRUD操作
     */

    private Map<String, Object> tempDataMap;

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

        String result = this.grpcScriptExec.exec(new CadContent(path)).getResult();
        tempDataMap = JSON.parseObject(result);

        return result;
    }


    @Resource
    private CadDataServiceImpl dataService;

    // 数据库CRUD接口
    @PostMapping("/create")
    public Object create() {

        if (this.tempDataMap != null) {
            for (String obj : tempDataMap.keySet()) {
                Object value = tempDataMap.get(obj);

                dataService.save(new CadData(obj, value.toString()));
                System.out.println("Saving to database: Key = " + obj + ", Value = " + value);
            }
            tempDataMap = null;
        } else {
            return ResponseEntity.badRequest().body("结果为空或未执行算法");
        }
        return ResponseEntity.ok("create success");
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
        UpdateWrapper<CadData> updateWrapper = new UpdateWrapper<>();
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
