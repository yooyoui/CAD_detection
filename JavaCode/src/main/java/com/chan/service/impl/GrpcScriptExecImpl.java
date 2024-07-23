package com.chan.service.impl;

import com.chan.entity.RequestContent;
import com.chan.entity.ScriptExecResult;
import com.chan.proto.CadDetProto;
import com.chan.proto.CadDetServiceGrpc;
import com.chan.service.IScriptExec;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrpcScriptExecImpl implements IScriptExec {

    private CadDetServiceGrpc.CadDetServiceBlockingStub cadStub;

    @GrpcClient("CadDetServiceGrpc")
    public void setCadStub(CadDetServiceGrpc.CadDetServiceBlockingStub cadStub) {
        this.cadStub = cadStub;
    }

    @Override
    public ScriptExecResult exec(RequestContent path) {
        // 获取Cad图像的物理路径
        String p = path.getPath();
        // 参数1判空
        // 如果p为空，则返回一个ScriptExecResult对象，结果为null
        if (p.isEmpty()) return new ScriptExecResult();

        // 构建请求
        CadDetProto.CadDetRequest.Builder builder = CadDetProto.CadDetRequest.newBuilder()
                .setFilePath(p);

        // 执行请求
        CadDetProto.CadDetResponse response = cadStub.execute(builder.build());

        // 获取响应结果
        String fileName = response.getFileName();
        List<CadDetProto.DetInfo> detInfoList = response.getDetInfoList();

        ScriptExecResult scriptExecResult = new ScriptExecResult(fileName, detInfoList);

        try {
            return scriptExecResult;
        } catch (io.grpc.StatusRuntimeException e) {
            throw new RuntimeException("script exec error,msg: " + e.getMessage(), e);
        }
    }
}