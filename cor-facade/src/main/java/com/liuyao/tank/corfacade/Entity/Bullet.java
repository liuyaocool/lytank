package com.liuyao.tank.corfacade.Entity;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.corfacade.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    public static BufferedImage bulletU = ImgUtil.readImg("img/tank/Bullet.png"),
            bulletL = ImgUtil.rotateImage(bulletU, -90),
            bulletR = ImgUtil.rotateImage(bulletU, 90),
            bulletD = ImgUtil.rotateImage(bulletU, 180);
    private static final int SPEED = 20;

    public boolean living = true;
    public TkGroup group;
    public TkDir dir;

    public Bullet(int x, int y, TkDir dir, TkGroup group) {
        super(x, y);
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g){
        if (!living) GameModel.getInstance().remove(this);
        BufferedImage img;
        switch (dir) {
            case LEFT: img = bulletL; break;
            case RIGHT: img = bulletR; break;
            case UP: img = bulletU; break;
            case DOWN: img = bulletD; break;
            default: return;
        }
        g.drawImage(img, x, y, null);
        this.width = img.getWidth();
        this.height = img.getHeight();

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }

        updateRect();

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    public void die(){
        this.living = false;
    };
}
