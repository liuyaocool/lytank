package com.liuyao.design_patterns.proxy;

public class MainProxy {

    /**
     * 静态代理
     *  像装饰者
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
        // 静态代理
        new LogProxy(new TimeProxy(new Tankp())).move();
    }

}

class TimeProxy implements Moveable {

    Moveable m;

    public TimeProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long t = System.currentTimeMillis();
        m.move();
        System.out.println("cost: " + (System.currentTimeMillis() - t));
    }
}

class LogProxy implements Moveable {

    Moveable m;

    public LogProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("log: start move");
        m.move();
        System.out.println("log: end move");
    }
}
