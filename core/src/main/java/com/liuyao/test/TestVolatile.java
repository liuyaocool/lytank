package com.liuyao.test;

public class TestVolatile {

    /**
     * 加VM参数 -Xint
     */

    static boolean flag;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("start");
            while (!flag) {

            }
            System.out.println("end");
        }).start();
        Thread.sleep(1000);
        flag = true;

    }
}
