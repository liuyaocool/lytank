package com.liuyao.design_patterns.composite_flyweight;

public class MainFlyweight {

    /**
     * 享元 重复利用对象
     *  池化思想
     *  String 常量池
     *
     * 与composite同用
     *  两个不同的对象组合成一个东西 放到池子里
     * @param args
     */
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = "a" + "bc";
        String s4 = new String("abc");
        String s5 = new String("abc");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s4 == s5);
        System.out.println(s4.intern() == s1);
        System.out.println(s4.intern() == s5.intern());

    }
}
