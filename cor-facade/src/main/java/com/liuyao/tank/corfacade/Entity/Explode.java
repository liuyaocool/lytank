package com.liuyao.tank.corfacade.Entity;

import com.liuyao.tank.core.ImgUtil;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.corfacade.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;

// 爆炸
public class Explode extends GameObject {

    public static BufferedImage[] explodes = new BufferedImage[16];
    public static final int WIDTH, HEIGHT;
    static {
        for(int i=0; i<16; i++) explodes[i] = ImgUtil.readImg("img/tank/e" + (i+1) + ".gif");
        HEIGHT = explodes[0].getHeight();
        WIDTH = explodes[0].getWidth();
    }

    private int step = 0;
    protected boolean living = true;
    protected TkGroup group;

    public Explode(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(explodes[step++], this.x, this.y, null);
        if (step >= explodes.length) GameModel.getInstance().remove(this);
    }

    protected void die(){
        this.living = false;
    };

}
