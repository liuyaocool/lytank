package com.liuyao.tank.corfacade;

import com.liuyao.tank.core.PropertyMgr;
import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;
import com.liuyao.tank.corfacade.Entity.GameObject;
import com.liuyao.tank.corfacade.Entity.Tank;
import com.liuyao.tank.corfacade.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final GameModel INSTANCE = new GameModel();
    static {
        INSTANCE.init();
    }

    public Tank myTank;

    private List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    private GameModel(){}

    private void init() {
        myTank= new Tank(200, 200, TkDir.DOWN, TkGroup.GOOD);
//        add(myTank);

        int tankcount = Integer.parseInt(PropertyMgr.getProperty("initTankCount"));
        // 初始化敌方坦克
        for (int i = 0; i < tankcount; i++) {
            new Tank(100 + i * 80, 100, TkDir.DOWN, TkGroup.BAD);
        }
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void add(GameObject o) {
        objects.add(o);
    }

    public void remove(GameObject o) {
        boolean remove = objects.remove(o);
        if (remove && o instanceof Tank) System.out.println(o);
    }

    public void paint(Graphics g) {
        // 窗口需要重新绘制的时候 自动调用
        // x,y 是从整个窗口左上角为(0,0) 包括关闭按钮

        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("bullets: " + bullets.size(), 10, 60);
//        g.drawString("tanks: " + tanks.size(), 10, 80);
//        g.drawString("explodes: " + explodes.size(), 10, 100);
//        g.setColor(c);

        myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = i+1; j < objects.size(); j++) {
                chain.collide(objects.get(i), objects.get(j));
            }
        }

    }

}
