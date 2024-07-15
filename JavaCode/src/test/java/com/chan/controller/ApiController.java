package com.chan.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chan.entity.CadContent;
import com.chan.entity.CadData;
import com.chan.service.impl.CadDataServiceImpl;
import com.chan.service.impl.GrpcScriptExecImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RequestMapping("/CadDet")
@RestController
public class ApiController {
    /**
     * 功能：1. 调用python脚本并返回结果
     * 2. 数据库CRUD操作
     */

    private Map<String, Object> tempDataMap = new HashMap<>();

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

        return this.grpcScriptExec.exec(new CadContent(path)).getResult();
    }


    @Resource
    private CadDataServiceImpl dataService;

    // 数据库CRUD接口
    @PostMapping("/create")
    public String create() {
        if (this.tempDataMap != null) {
            for (Map.Entry<String, Object> entry : tempDataMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // 假设有一个方法 saveToDatabase，接受两个参数：列名和列值
                // saveToDatabase(key, value);
                System.out.println("Saving to database: Key = " + key + ", Value = " + value);
            }
            tempDataMap.clear();
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
    public Object update(@RequestParam int id, String position, String value) {
        UpdateWrapper<CadData> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("position", position);
        updateWrapper.set("value", value);
        dataService.update(updateWrapper);
        return "update success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam int id) {
        dataService.removeById(id);
        return "delete success";
    }

}
