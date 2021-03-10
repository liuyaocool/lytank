package com.liuyao.tank.corfacade;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.core.TkGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    public Tank myTank = new Tank(200, 200, TkDir.DOWN, TkGroup.GOOD);

    private List<GameObject> objects = new ArrayList<>();

    private GameModel(){}

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void add(GameObject o) {
        objects.add(o);
    }

    public void remove(GameObject o) {
        objects.remove(this);
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



    }

}
