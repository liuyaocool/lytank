package com.liuyao.design_patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainProxy {

    public static void main(String[] args) {
        System.out.println();
        Tankp tank = new Tankp();
        // 静态代理 像装饰者
        new LogProxy(new TimeProxy(tank)).move();

        /**
         * 动态代理
         *  jdk
         *  instrument
         *      class load到内存过程中 拦截
         *      需要了解class中每一个0 1 表示什么意思
         *      最灵活 也最难
         *  cglib
         *      底层用的asm
         *      代理类是被代理类的子类
         *      被代理类不能是final
         */

        // 保存中间生成的代理.class $Proxy0.class
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        // 生成class字节码 底层使用的是asm
        Moveable m = (Moveable) Proxy.newProxyInstance(Tankp.class.getClassLoader(),
                new Class[]{Moveable.class},
                new TimeDProxy(tank));

        m.move();

    }

}

class TimeDProxy implements InvocationHandler {

    Tankp tankp;

    public TimeDProxy(Tankp tankp) {
        this.tankp = tankp;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dproxy log: start-" + method.getName());
        Object invoke = method.invoke(tankp, args);
        System.out.println("dproxy log: end-" + method.getName());
        return invoke;
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
