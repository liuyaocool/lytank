package com.liuyao.tank.normal;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends TankParent{

    public static BufferedImage bulletU = readImg("img/tank/Bullet.png"),
            bulletL = rotateImage(bulletU, -90),
            bulletR = rotateImage(bulletU, 90),
            bulletD = rotateImage(bulletU, 180);
    private static final int SPEED = 20;

    private TkDir dir;

    public Bullet(int x, int y, TkDir dir, TankFrame tf, TkGroup group) {
        super(tf, x, y);
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g){
        if (!living) tankFrame.bullets.remove(this);
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

    //碰撞检测
    public void collideWith(Tank tank) {
        if (this.group == tank.group) return;
        if (this.rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            tankFrame.explodes.add(new Explode(this.tankFrame, this.x, this.y));
        }
    }
}
