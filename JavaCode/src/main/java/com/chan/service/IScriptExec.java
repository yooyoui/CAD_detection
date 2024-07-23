package com.chan.service;

import com.chan.entity.RequestContent;
import com.chan.entity.ScriptExecResult;

public interface IScriptExec {
    ScriptExecResult exec(RequestContent path);
}
