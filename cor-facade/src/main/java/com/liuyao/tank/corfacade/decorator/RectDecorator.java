package com.liuyao.tank.corfacade.decorator;

import com.liuyao.tank.corfacade.Entity.GameObject;
import com.liuyao.tank.corfacade.Entity.Tank;

import java.awt.*;

public class RectDecorator extends GODEcorator {

    public RectDecorator(GameObject gmo) {
        super(gmo);
    }

    @Override
    public void decPaint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(this.x, this.y, this.width, this.height);
        g.setColor(c);
    }
}
