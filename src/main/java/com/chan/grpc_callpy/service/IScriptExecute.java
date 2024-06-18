package com.chan.grpc_callpy.service;

import com.chan.grpc_callpy.entity.ScriptContent;
import com.chan.grpc_callpy.entity.ScriptExecResult;

public interface IScriptExecute {
    ScriptExecResult exec(ScriptContent content);
}
