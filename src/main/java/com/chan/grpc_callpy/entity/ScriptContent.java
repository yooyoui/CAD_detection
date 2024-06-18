package com.chan.grpc_callpy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptContent {
    private String content;
    private List<String> extractParams;

}
