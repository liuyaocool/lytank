package com.liuyao.design_patterns.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpringAOP {

    public static void main(String[] args) {

        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        /**
         * 注意
         *  这里 如果tank类实现了接口 则需要用接口来接收
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
        Tanko d = (Tanko) context.getBean("tanko");
        d.move();
    }
}
