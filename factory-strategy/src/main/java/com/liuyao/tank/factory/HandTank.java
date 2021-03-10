package com.liuyao.tank.factory;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HandTank extends FacTank {

    private static final int SPEED = 5;
    protected static BufferedImage u = ImgUtil.readImg("img/tank/GoodTank.png"),
            l = ImgUtil.rotateImage(u, -90),
            r = ImgUtil.rotateImage(u, 90),
            d = ImgUtil.rotateImage(u, 180);

    public HandTank(com.liuyao.tank.FacTankFrame tankFrame, FacSkinFactory factory, int x, int y, TkDir dir) {
        super(tankFrame, factory, x, y);
        this.factory = factory;
        this.group = TkGroup.HAND;
        this.dir = dir;
        this.moving = false;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.tanks.remove(this);
            return;
        }
        BufferedImage img;
        switch (this.dir) {
            case LEFT: img = l; break;
            case RIGHT: img = r; break;
            case UP: img = u; break;
            case DOWN: img = d; break;
            default: return;
        }
        g.drawImage(img, x, y, null);
        this.width = img.getWidth();
        this.height = img.getHeight();

        move();
    }

    public void fire(){
        bulletStrategy.fire(this);
    }

    protected void move() {
        if (!this.moving) return;
        switch (this.dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }
        // 边界检测
        boundsCheck();
        updateRect();
    }

}
