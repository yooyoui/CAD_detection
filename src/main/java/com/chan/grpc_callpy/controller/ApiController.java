package com.chan.grpc_callpy.controller;

import com.chan.grpc_callpy.entity.ScriptContent;
import com.chan.grpc_callpy.service.impl.GrpcScriptExecuterImpl;
import com.chan.grpc_callpy.util.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chan
 * @date 2024/6/11
 */

// 定义路由前缀，所有的路由都会在这个前缀之后
@RequestMapping("/main/proto/")
// 定义一个控制器
@RestController
public class ApiController {

    // 使用@Autowired注解和setter方法注入GrpcScriptExecuter对象
    private GrpcScriptExecuterImpl grpcScriptExecuter;
    @Autowired
    public void setGrpcScriptExecuter(GrpcScriptExecuterImpl grpcScriptExecuter) {
        this.grpcScriptExecuter = grpcScriptExecuter;
    }

    // 定义一个路由，请求方式为POST，路径为callpy
    @PostMapping("callpy")
    public String script(@RequestBody Map<String, Object> request) {
        // 从请求中获取content_base64参数，如果没有则返回空字符串
        String contentBase64 = (String) request.getOrDefault("content_base64", "");
        if (contentBase64.isEmpty()) {
            return "";
        }
        // 从请求中获取extract_params参数，如果没有则返回一个空的List
        List<String> extractParams = (List<String>) request.getOrDefault("extract_params", new ArrayList<>());
        // 调用GrpcScriptExecuterImpl的exec方法，传入一个ScriptContent对象,并返回结果
        return this.grpcScriptExecuter.exec(
                new ScriptContent(
                        Base64Utils.base64Decode(contentBase64),
                        extractParams
                )
        ).getResult();
    }
}
