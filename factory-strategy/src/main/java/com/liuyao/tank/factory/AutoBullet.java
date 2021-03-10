package com.liuyao.tank.factory;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.FacTankFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AutoBullet extends FacBullet {

    public static BufferedImage u = ImgUtil.readImg("img/tank/Bullet.png"),
            l = ImgUtil.rotateImage(u, -90),
            r = ImgUtil.rotateImage(u, 90),
            d = ImgUtil.rotateImage(u, 180);
    private static final int SPEED = 20;

    public TkDir dir;

    public AutoBullet(FacTankFrame tankFrame, FacSkinFactory fac, int x, int y, TkDir dir) {
        super(tankFrame, fac, x, y);
        this.factory = fac;
        this.dir = dir;
        this.group = TkGroup.AUTO;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
            return;
        }
        BufferedImage img;
        switch (dir) {
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

    @Override
    public BufferedImage getImgU() {
        return u;
    }

    @Override
    public BufferedImage getImgL() {
        return l;
    }

    @Override
    public BufferedImage getImgR() {
        return r;
    }

    @Override
    public BufferedImage getImgD() {
        return d;
    }

    @Override
    protected void move() {
        switch (dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }

        updateRect();

        if (x < 0 || y < 0 || x > FacTankFrame.GAME_WIDTH || y > FacTankFrame.GAME_HEIGHT) living = false;
    }

}
