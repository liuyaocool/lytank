package com.liuyao.tank.corfacade.decorator;

import com.liuyao.tank.corfacade.Entity.GameObject;

import java.awt.*;

public class LineDecorator extends GODEcorator {

    public LineDecorator(GameObject gmo) {
        super(gmo);
    }

    @Override
    public void decPaint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(this.x, this.y, this.x + this.width, this.y + this.height);
        g.setColor(c);
    }
}
