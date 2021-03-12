package com.liuyao.tank.corfacade.decorator;

import com.liuyao.tank.corfacade.Entity.GameObject;

import java.awt.*;

public abstract class GODEcorator extends GameObject {

    private GameObject go;

    public GODEcorator(GameObject gmo) {
        super(gmo.x, gmo.y);
        this.go = gmo;
    }

    @Override
    public void paint(Graphics g) {
        updateSize();
        updateRect();
        decPaint(g);
        this.go.paint(g);
    }

    public abstract void decPaint(Graphics g);

    public void updateSize() {
        this.x = this.go.x;
        this.y = this.go.y;
        this.width = this.go.width;
        this.height = this.go.height;
    }
}
