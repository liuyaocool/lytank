package com.liuyao.tank.normal;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkGroup;

import java.awt.*;

public abstract class TankParent extends ImgUtil {

    protected TankFrame tankFrame;
    protected int x, y, width, height;
    protected boolean living = true;
    protected TkGroup group;
    protected Rectangle rectangle;

    public TankParent(TankFrame tankFrame, int x, int y) {
        this.tankFrame = tankFrame;
        this.x = x;
        this.y = y;
    }

    protected void updateRect(){
        if (null == this.rectangle) rectangle = new Rectangle();

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = this.width;
        this.rectangle.height = this.height;
    }

    protected void die(){
        this.living = false;
    };

    public abstract void paint(Graphics g);

    //=================== normal get set ===============
    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isLiving() {
        return living;
    }

    public TkGroup getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
