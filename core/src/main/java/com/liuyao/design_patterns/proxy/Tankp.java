package com.liuyao.design_patterns.proxy;

import java.util.Random;

public class Tankp implements Moveable{

    @Override
    public void move() {
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
