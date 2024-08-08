package com.det.service;

import com.det.entity.RequestContent;
import com.det.entity.ScriptExecResult;

public interface IScriptExec {
    ScriptExecResult exec(RequestContent path);
}
