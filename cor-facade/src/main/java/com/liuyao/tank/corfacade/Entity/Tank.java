package com.liuyao.tank.corfacade.Entity;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.corfacade.GameModel;
import com.liuyao.tank.corfacade.TankFrame;
import com.liuyao.tank.corfacade.decorator.LineDecorator;
import com.liuyao.tank.corfacade.decorator.RectDecorator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends GameObject {

    private static final int SPEED = 5;
    public static BufferedImage goodTankU = ImgUtil.readImg("img/tank/GoodTank.png"),
            goodTankL = ImgUtil.rotateImage(goodTankU, -90),
            goodTankR = ImgUtil.rotateImage(goodTankU, 90),
            goodTankD = ImgUtil.rotateImage(goodTankU, 180);
    public static BufferedImage badTankU = ImgUtil.readImg("img/tank/BadTank.png"),
            badTankL = ImgUtil.rotateImage(badTankU, -90),
            badTankR = ImgUtil.rotateImage(badTankU, 90),
            badTankD = ImgUtil.rotateImage(badTankU, 180);

    public boolean living = true;
    public TkGroup group;

    private int oldx, oldy;
    private TkDir dir;
    private boolean moving = true;
    private Random random = new Random();

    public TkDir getDir() { return dir; }
    public void setDir(TkDir dir) { this.dir = dir; }
    public boolean getMoving() { return moving; }
    public void setMoving(boolean moving) { this.moving = moving; }

    public Tank(int x, int y, TkDir dir, TkGroup group) {
        super(x, y);
        this.oldx = x;
        this.oldy = y;
        this.dir = dir;
        this.group = group;
        if (TkGroup.GOOD == this.group){
            this.moving = false;
        }
    }

    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
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
        oldx = this.x;
        oldy = this.y;
        switch (dir) {
            case LEFT: x -= SPEED; break;
            case RIGHT: x += SPEED; break;
            case UP: y -= SPEED; break;
            case DOWN: y += SPEED; break;
        }
        if (this.group == TkGroup.BAD && random.nextInt(100) > 95) this.fire();

        if (this.group == TkGroup.BAD && random.nextInt(100) > 95) randomDir();

        // 边界检测
        boundsCheck();

        updateRect();
    }

    public void back(){
        this.x = oldx;
        this.y = oldy;
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
        int bx = tank.x + tank.width/2 - bimg.getWidth()/2;
        int by = tank.y + tank.height/2 - bimg.getHeight()/2;
        GameModel.getInstance().add(new Bullet(bx, by, tank.getDir(), tank.group));
        // 装饰者模式
//        GameModel.getInstance().add(new LineDecorator(new RectDecorator(
//                new Bullet(bx, by, tank.getDir(), tank.group))));
    }

    public void die(){
        this.living = false;
    };

}
