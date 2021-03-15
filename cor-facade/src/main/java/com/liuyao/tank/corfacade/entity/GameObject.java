package com.liuyao.tank.corfacade.entity;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {

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
