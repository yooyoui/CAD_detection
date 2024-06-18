package com.chan.grpc_callpy.service.impl;

import com.chan.grpc_callpy.entity.ScriptContent;
import com.chan.grpc_callpy.entity.ScriptExecResult;
import com.chan.grpc_callpy.proto.CallpyProto;
import com.chan.grpc_callpy.proto.CallpyServiceGrpc;
import com.chan.grpc_callpy.service.IScriptExecute;
import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrpcScriptExecuterImpl implements IScriptExecute {

    // 使用@GrpcClient注解，将CallpyServiceGrpc.CallpyServiceBlockingStub注入到GrpcScriptExecuter中
    private CallpyServiceGrpc.CallpyServiceBlockingStub scriptStub;
    @GrpcClient("CallpyServiceGrpc")
    public void setScriptStub(CallpyServiceGrpc.CallpyServiceBlockingStub scriptStub) {
        this.scriptStub = scriptStub;
    }

    // 实现ScriptExecute接口的exec方法
    @Override
    public ScriptExecResult exec(ScriptContent content) {
        // 获取ScriptContent对象的content属性
        String c = content.getContent();
        // 参数1判空
        if (c.isEmpty()) return new ScriptExecResult(null);
        // 获取ScriptContent对象的extractParams属性
        List<String> extractParams = content.getExtractParams();
        // 创建一个ScriptProto.ScriptRequest.Builder对象，设置content属性为c
        CallpyProto.ScriptRequest.Builder r = CallpyProto.ScriptRequest.newBuilder()
                .setContent(c);
        // 参数2判空
        if (!extractParams.isEmpty()) {
            ListValue.Builder lvb = ListValue.newBuilder();
            for (String ep : extractParams) {
                lvb.addValues(Value.newBuilder().setStringValue(ep).build());
            }
            // 将ListValue对象设置到ScriptRequest对象的extractParams属性中
            r.setExtractParams(lvb.build());
        }
        try {
            // 调用scriptStub的execute方法，传入ScriptRequest对象，并返回一个ScriptExecResult对象
            return new ScriptExecResult(scriptStub.execute(r.build()).getResult());
        } catch (io.grpc.StatusRuntimeException e) {
            // 如果调用失败，则抛出一个RuntimeException异常
            throw new RuntimeException("script exec error,msg: " + e.getMessage(), e);
        }
    }
}
