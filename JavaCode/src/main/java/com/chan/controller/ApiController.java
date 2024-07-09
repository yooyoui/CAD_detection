package com.chan.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chan.entity.CadContent;
import com.chan.entity.Data;
import com.chan.service.impl.DataServiceImpl;
import com.chan.service.impl.GrpcScriptExecImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/CadDet")
@RestController
public class ApiController {
    /**
     * 功能：1. 调用python脚本并返回结果
     * 2. 数据库CRUD操作
     */

    private Data tempData; // 存储py脚本执行的数据（临时）

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

        Data data = new Data(this.grpcScriptExec.exec(new CadContent(path))
                .getResult());
        this.tempData = data;
        return data.getContent();
    }


    @Resource
    private DataServiceImpl dataService;

    // 数据库CRUD接口
    @PostMapping("/create")
    public String create() {
        if (this.tempData != null) {
            dataService.save(this.tempData);
            tempData = null;
        } else {
            return "结果为空或未执行算法";
        }
        return "create success";
    }

    @GetMapping("/read")
    public Object read() {
        return dataService.list();
    }

    @PutMapping("/update")
    public Object update(int id, String content) {
        UpdateWrapper<Data> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("content", content);
        dataService.update(updateWrapper);
        return "update success";
    }

    @DeleteMapping("/delete")
    public String delete(int id) {
        dataService.removeById(id);
        return "delete success";
    }

}
