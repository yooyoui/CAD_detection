package com.det.entity;

import com.det.proto.CadDetProto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptExecResult {
    private String fileName;
    private List<CadDetProto.DetInfo> result;
    private byte[] image;
}
