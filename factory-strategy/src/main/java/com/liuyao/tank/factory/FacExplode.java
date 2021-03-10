package com.liuyao.tank.factory;

import com.liuyao.tank.FacTankFrame;

public abstract class FacExplode extends FacTankParent {

    public FacExplode(FacTankFrame tankFrame, FacSkinFactory factory, int x, int y) {
        super(tankFrame, factory, x, y);
    }
}
