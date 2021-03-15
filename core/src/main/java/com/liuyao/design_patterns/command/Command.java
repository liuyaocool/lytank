package com.liuyao.design_patterns.command;

public abstract class Command {

    public abstract void execute(); // 执行
    public abstract void undo(); // 回退
}
