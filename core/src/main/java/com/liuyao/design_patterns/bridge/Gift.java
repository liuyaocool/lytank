package com.liuyao.design_patterns.bridge;

public abstract class Gift {
    protected GiftImpl impl;

    public Gift(GiftImpl impl) {
        this.impl = impl;
    }
}
