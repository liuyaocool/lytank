package com.liuyao.design_patterns.bridge;

public class MainBridge {

    /**
     * 桥接模式 双维度扩展
     *  分离抽象与具体
     *  用聚合的方式（桥）连接抽象与具体
     *  抽象与具体两个维度上反别发展 不会产生类爆炸(?)
     * @param args
     */
    public static void main(String[] args) {

        Gift gift = new WarmGift(new Flower()); // 温暖的花

        Gift gift2 = new MarrryGift(new Flower()); // 结婚的花

        Gift gift1 = new MarrryGift(new Ring()); // 结婚的戒指

    }
}
