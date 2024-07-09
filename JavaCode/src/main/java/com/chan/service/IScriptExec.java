package com.chan.service;

import com.chan.entity.CadContent;
import com.chan.entity.ScriptExecResult;

public interface IScriptExec {
    ScriptExecResult exec(CadContent path);
}
