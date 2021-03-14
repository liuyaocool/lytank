package com.liuyao.design_patterns.proxy;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 加配置： <aop:aspectj-autoproxy />
 */
@Aspect
public class TimeSpringProxy {

    @Before("execution(void com.liuyao.design_patterns.proxy.Tanko.move())")
    public void before() {
        System.out.println("TimeSpringProxy befoer execute " + System.currentTimeMillis());
    }

    @After("execution(void com.liuyao.design_patterns.proxy.Tanko.move())")
    public void after() {
        System.out.println("TimeSpringProxy after execute " + System.currentTimeMillis());
    }

}
