package com.liuyao.design_patterns.proxy;

public class TimeSpringProxy {

    public void before() {
        System.out.println("TimeSpringProxy befoer execute " + System.currentTimeMillis());
    }

    public void after() {
        System.out.println("TimeSpringProxy after execute " + System.currentTimeMillis());
    }

}
