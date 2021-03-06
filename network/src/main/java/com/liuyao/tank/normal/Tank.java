package com.liuyao.tank.normal;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends TankParent {

    private static final int SPEED = 5;
    public static BufferedImage goodTankU = readImg("img/tank/GoodTank.png"),
            goodTankL = rotateImage(goodTankU, -90),
            goodTankR = rotateImage(goodTankU, 90),
            goodTankD = rotateImage(goodTankU, 180);
    public static BufferedImage badTankU = readImg("img/tank/BadTank.png"),
            badTankL = rotateImage(badTankU, -90),
            badTankR = rotateImage(badTankU, 90),
            badTankD = rotateImage(badTankU, 180);


    private TkDir dir = TkDir.DOWN;
    private boolean moving = true;
    private Random random = new Random();

    public TkDir getDir() { return dir; }
    public void setDir(TkDir dir) { this.dir = dir; }
    public boolean getMoving() { return moving; }
    public void setMoving(boolean moving) { this.moving = moving; }

    public Tank(int x, int y, TkDir dir, TankFrame tf, TkGroup group) {
        super(tf, x, y);
        this.dir = dir;
        this.group = group;
        if (TkGroup.GOOD == this.group){
            this.moving = false;
        }
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.tanks.remove(this);
        }
        BufferedImage img;
        switch (this.group){
            case BAD:
                switch (this.dir) {
                    case LEFT: img = badTankL; break;
                    case RIGHT: img = badTankR; break;
                    case UP: img = badTankU; break;
                    case DOWN: img = badTankD; break;
                    default: return;
                }
                break;
            case GOOD:
                switch (this.dir) {
                    case LEFT: img = goodTankL; break;
                    case RIGHT: img = goodTankR; break;
                    case UP: img = goodTankU; break;
                    case DOWN: img = goodTankD; break;
                    default: return;
                }
                break;
            default: return;
        }
        g.drawImage(img, x, y, null);
        this.width = img.getWidth();
        this.height = img.getHeight();
        
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }
        if (this.group == TkGroup.BAD && random.nextInt(100) > 95) this.fire();

        if (this.group == TkGroup.BAD && random.nextInt(100) > 95) randomDir();

        // ????????????
        boundsCheck();

        updateRect();

    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- this.width -2) x = TankFrame.GAME_WIDTH - this.width -2;
        if (this.y > TankFrame.GAME_HEIGHT - this.height -2 ) y = TankFrame.GAME_HEIGHT -this.height -2;
    }

    private void randomDir() {
        this.dir = TkDir.values()[random.nextInt(4)];
    }

    public void fire() {
        Tank tank = this;
        BufferedImage bimg;
        switch (tank.getDir()){
            case LEFT: bimg = Bullet.bulletL; break;
            case UP: bimg = Bullet.bulletU; break;
            case RIGHT: bimg = Bullet.bulletR; break;
            case DOWN: bimg = Bullet.bulletD; break;
            default: return;
        }
        int bx = tank.getX() + tank.getWidth()/2 - bimg.getWidth()/2;
        int by = tank.getY() + tank.getHeight()/2 - bimg.getHeight()/2;
        Bullet b = new Bullet(bx, by, tank.getDir(), tank.getTankFrame(), tank.getGroup());
        tank.getTankFrame().bullets.add(b);
    }
}
