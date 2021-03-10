package com.liuyao.tank.factory;

import com.liuyao.tank.core.TkGroup;

import java.awt.*;

public abstract class FacTankParent {

    public com.liuyao.tank.FacTankFrame tankFrame;
    public FacSkinFactory factory;
    public int x, y, width, height;
    public boolean living = true;
    public TkGroup group;
    public Rectangle rectangle;

    public FacTankParent(com.liuyao.tank.FacTankFrame tankFrame, FacSkinFactory factory, int x, int y) {
        this.tankFrame = tankFrame;
        this.factory = factory;
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

}
