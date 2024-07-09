package com.chan.service.impl;

import com.chan.entity.CadContent;
import com.chan.entity.ScriptExecResult;
import com.chan.proto.CallPyProto;
import com.chan.proto.CallPyServiceGrpc;
import com.chan.service.IScriptExec;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class GrpcScriptExecImpl implements IScriptExec {

    private CallPyServiceGrpc.CallPyServiceBlockingStub scriptStub;

    @GrpcClient("CallPyServiceGrpc")
    public void setScriptStub(CallPyServiceGrpc.CallPyServiceBlockingStub scriptStub) {
        this.scriptStub = scriptStub;
    }

    @Override
    public ScriptExecResult exec(CadContent path) {
        // 获取Cad图像的物理路径
        String p = path.getPath();
        // 参数1判空
        // 如果p为空，则返回一个ScriptExecResult对象，结果为null
        if (p.isEmpty()) return new ScriptExecResult(null);
        CallPyProto.ScriptRequest.Builder r = CallPyProto.ScriptRequest.newBuilder()
                .setContent(p);
        try {
            return new ScriptExecResult(scriptStub.execute(r.build()).getResult());
        } catch (io.grpc.StatusRuntimeException e) {
            throw new RuntimeException("script exec error,msg: " + e.getMessage(), e);
        }
    }
}
