package com.chan.service.impl;

import com.chan.entity.RequestContent;
import com.chan.entity.ScriptExecResult;
import com.chan.proto.CadDetProto;
import com.chan.proto.CadDetServiceGrpc;
import com.chan.service.IScriptExec;
import com.google.protobuf.ByteString;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
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

        byte[] origImg;
        try {
            // 读取文件
            origImg = Files.readAllBytes(Paths.get(p));
        } catch (NoSuchFileException e) {
            throw new RuntimeException("File not found at path: " + p, e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file at path: " + p, e);
        }
        if (origImg.length == 0) return new ScriptExecResult();

        // 构建请求
        CadDetProto.CadDetRequest.Builder builder = CadDetProto.CadDetRequest.newBuilder()
                .setOrigImage(ByteString.copyFrom(origImg));

        // 执行请求
        CadDetProto.CadDetResponse response = cadStub.execute(builder.build());

        // 获取响应结果
        String fileName = response.getFileName();
        List<CadDetProto.DetInfo> detInfoList = response.getDetInfoList();
        byte[] resultImg = response.getResultImage().toByteArray();
        ScriptExecResult scriptExecResult = new ScriptExecResult(fileName, detInfoList, resultImg);

        try {
            return scriptExecResult;
        } catch (io.grpc.StatusRuntimeException e) {
            throw new RuntimeException("script exec error,msg: " + e.getMessage(), e);
        }
    }
}