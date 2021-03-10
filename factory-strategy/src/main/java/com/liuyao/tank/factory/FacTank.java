package com.liuyao.tank.factory;

import com.liuyao.tank.FacTankFrame;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.Strategy.FacFireStrategy;
import com.liuyao.tank.Strategy.FacFireStrategy1;

import java.util.Random;

public abstract class FacTank extends FacTankParent {
    protected static final Random RANDOM = new Random();

    protected TkDir dir = TkDir.DOWN;
    protected boolean moving = true;
    protected FacFireStrategy bulletStrategy = FacFireStrategy1.neww();

    public FacFireStrategy getBulletStrategy() { return bulletStrategy; }
    public void setBulletStrategy(FacFireStrategy bulletStrategy) { this.bulletStrategy = bulletStrategy; }
    public TkDir getDir() { return dir; }
    public void setDir(TkDir dir) { this.dir = dir; }
    public boolean getMoving() { return moving; }
    public void setMoving(boolean moving) { this.moving = moving; }

    public FacTank(com.liuyao.tank.FacTankFrame tankFrame, FacSkinFactory factory, int x, int y) {
        super(tankFrame, factory, x, y);
    }

    protected void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > FacTankFrame.GAME_WIDTH - this.width - 2) x = FacTankFrame.GAME_WIDTH - this.width - 2;
        if (this.y > FacTankFrame.GAME_HEIGHT - this.height - 2) y = FacTankFrame.GAME_HEIGHT -this.height - 2;
    }

    public abstract void fire();
    protected abstract void move();
}
