package com.liuyao.tank.corfacade.Entity;

import java.awt.*;

public abstract class GameObject {

    public int x, y, width, height;
    public Rectangle rect = new Rectangle();

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void updateRect(){
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = this.width;
        this.rect.height = this.height;
    }

    public abstract void paint(Graphics g);
}
