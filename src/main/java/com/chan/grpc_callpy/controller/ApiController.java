package com.chan.grpc_callpy.controller;

import com.chan.grpc_callpy.entity.Cad;
import com.chan.grpc_callpy.entity.ScriptContent;
import com.chan.grpc_callpy.service.impl.GrpcScriptExecuterImpl;
import com.chan.grpc_callpy.util.Base64Utils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

    // 定义一个静态变量 cad，用于存储 exec 执行后的结果
    @Getter
    private static Cad cad;

    private GrpcScriptExecuterImpl grpcScriptExecuter;
    @Autowired
    public void setGrpcScriptExecuter(GrpcScriptExecuterImpl grpcScriptExecuter) {
        this.grpcScriptExecuter = grpcScriptExecuter;
    }

    // 定义一个路由，请求方式为 POST，路径为 callpy
    @PostMapping("callpy")
    public Object script(@RequestBody Map<String, Object> request) {
        // 从请求中获取content_base64参数，如果没有则返回空字符串
        String contentBase64 = (String) request.getOrDefault("content_base64", "");
        // 同样，如果存在contentBase64参数，但参数为空，则返回一个空字符串
        if (contentBase64.isEmpty()) {
            return "";
        }
        // 从请求中获取extract_params参数，如果没有则返回一个空的List
        List<String> extractParams = (List<String>) request.getOrDefault("extract_params", new ArrayList<>());

        // 调用 grpcScriptExecuter 的 exec 方法， 用 content 接收返回值
        String content = this.grpcScriptExecuter.exec(
                new ScriptContent(
                        Base64Utils.base64Decode(contentBase64),
                        extractParams
                )
        ).getResult();

        // 初始化一个 Cad 对象，将 content 传入到静态变量 cad 中
        cad = new Cad();
        cad.setContent(content);
        System.out.println(getCad());

        return content;
    }
}
