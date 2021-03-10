package com.liuyao.tank.factory;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.FacTankFrame;

/**
 * 皮肤工厂
 *
 */
public abstract class FacSkinFactory {
    public abstract FacTank createTank(FacTankFrame tf);
    public abstract FacBullet createBullet(FacTankFrame tf, int x, int y, TkDir dir);
    public abstract FacExplode createExplode(FacTankFrame tf, int x, int y) ;
}
