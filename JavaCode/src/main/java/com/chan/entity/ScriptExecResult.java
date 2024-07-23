package com.chan.entity;

import com.chan.proto.CadDetProto;
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
}
