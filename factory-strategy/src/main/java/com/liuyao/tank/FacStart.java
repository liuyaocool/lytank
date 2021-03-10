package com.liuyao.tank;

import com.liuyao.tank.core.PropertyMgr;
import com.liuyao.tank.factory.AutoFactory;
import com.liuyao.tank.factory.FacSkinFactory;
import com.liuyao.tank.factory.HandFactory;

public class FacStart {

    public static void main(String[] args) {
        // 窗口类
        com.liuyao.tank.FacTankFrame tf = new com.liuyao.tank.FacTankFrame(new HandFactory());

        FacSkinFactory fac = new AutoFactory();
        int tankcount = Integer.parseInt(PropertyMgr.getProperty("initTankCount"));
        // 初始化敌方坦克
        for (int i = 0; i < tankcount; i++) {
            tf.tanks.add(fac.createTank(tf));
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
