package com.liuyao.tank.Strategy;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.factory.FacTank;

// 四个方向的子弹
public class FacFireStrategy4 implements FacFireStrategy {

    private static FacFireStrategy4 instance = new FacFireStrategy4();
    private FacFireStrategy4() {}
    public synchronized static FacFireStrategy4 neww(){
        return instance;
    }

    @Override
    public void fire(FacTank tank) {
        for (TkDir dir :TkDir.values()){
            tank.tankFrame.bullets.add(tank.factory.createBullet(tank.tankFrame, tank.x, tank.y, dir));
        }
    }
}
