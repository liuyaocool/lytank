package com.liuyao.tank.factory;

import com.liuyao.tank.core.TkDir;

public class HandFactory extends FacSkinFactory{

    @Override
    public FacTank createTank(com.liuyao.tank.FacTankFrame facTankFrame) {
        return new HandTank(facTankFrame, this,200, 200, TkDir.UP);
    }

    @Override
    public FacBullet createBullet(com.liuyao.tank.FacTankFrame tf, int x, int y, TkDir dir) {
        return new HandBullet(tf, this, x, y, dir);
    }

    @Override
    public FacExplode createExplode(com.liuyao.tank.FacTankFrame tf, int x, int y) {
        return new Explode(tf, this, x, y);
    }
}
