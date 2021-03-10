package com.liuyao.tank.factory;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.Strategy.FacFireStrategy;
import com.liuyao.tank.Strategy.FacFireStrategy1;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AutoTank extends FacTank {

    private static final int SPEED = 5;
    protected static BufferedImage u = ImgUtil.readImg("img/tank/BadTank.png"),
            l = ImgUtil.rotateImage(u, -90),
            r = ImgUtil.rotateImage(u, 90),
            d = ImgUtil.rotateImage(u, 180);

    protected FacFireStrategy bulletStrategy = FacFireStrategy1.neww();

    public AutoTank(com.liuyao.tank.FacTankFrame tankFrame, FacSkinFactory factory) {
        super(tankFrame, factory, RANDOM.nextInt(tankFrame.getWidth() - u.getWidth()), 20);
        this.dir = TkDir.values()[RANDOM.nextInt(4)];
        this.group = TkGroup.AUTO;
    }

    @Override
    public void fire() {
        bulletStrategy.fire(this);
    }

    @Override
    protected void move() {
        if (!this.moving) return;
        switch (this.dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }
        if (RANDOM.nextInt(100) > 95) this.fire();
        if (RANDOM.nextInt(100) > 95) randomDir();
        // 边界检测
        boundsCheck();
        updateRect();
    }

    private void randomDir() {
        this.dir = TkDir.values()[RANDOM.nextInt(4)];
    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            this.tankFrame.tanks.remove(this);
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

}
