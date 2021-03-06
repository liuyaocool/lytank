package com.liuyao.tank.normal;

import com.liuyao.tank.core.PropertyMgr;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;

public class Start {

    /**
     * 代码重构
     *  工厂:
     *      抽象出父类 用到哪个抽哪个 不要一次性全部抽象 --这样思路不清晰 容易乱
     *      然后 一步一测试
     *
     * 进度: 8.3 00:21:00
     * 下一模式需要b站先看马士兵责任链
     * @param args
     */
    public static void main(String[] args) {
        // 窗口类
        TankFrame tf = new TankFrame();

        int tankcount = Integer.parseInt(PropertyMgr.getProperty("initTankCount"));
        // 初始化敌方坦克
        for (int i = 0; i < tankcount; i++) {
            tf.tanks.add(new Tank(100 + i * 80, 100, TkDir.DOWN, tf, TkGroup.BAD));
        }

        while (true) {
            try {
                Thread.sleep(50);
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }
}
