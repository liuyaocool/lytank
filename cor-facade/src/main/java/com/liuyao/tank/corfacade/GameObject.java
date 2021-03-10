package com.liuyao.tank.corfacade;

import java.awt.*;

public abstract class GameObject {

    public int x, y, width, height;
    public Rectangle rectangle = new Rectangle();

    protected void updateRect(){
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = this.width;
        this.rectangle.height = this.height;
    }

    public abstract void paint(Graphics g);
}
