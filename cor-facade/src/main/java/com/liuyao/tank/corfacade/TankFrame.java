package com.liuyao.tank.corfacade;

import com.liuyao.tank.core.TkDir;
import com.liuyao.tank.corfacade.Entity.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() throws HeadlessException {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);

        this.addKeyListener(new TankKeyAdapter());
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        // 用双缓冲解决闪烁问题（不重要）
        // repaint - update
        // 截获update
        // 首先把该画出来的东西（坦克， 子弹）先画在内存的图片中，图片大小和游戏画面一致
        // 把内存中图片一次性画到屏幕上（内存的内容复制到显存）
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);
    }

    class TankKeyAdapter extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: bL = true; break;
                case KeyEvent.VK_UP: bU = true; break;
                case KeyEvent.VK_RIGHT: bR = true; break;
                case KeyEvent.VK_DOWN: bD = true; break;
                default: break;
            }
            setMainDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: bL = false; break;
                case KeyEvent.VK_UP: bU = false; break;
                case KeyEvent.VK_RIGHT: bR = false; break;
                case KeyEvent.VK_DOWN: bD = false; break;
                case KeyEvent.VK_Q: GameModel.getInstance().myTank.fire();break;
                default: break;
            }
            setMainDir();
        }

        private void setMainDir() {
            Tank myTank = GameModel.getInstance().myTank;
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(TkDir.LEFT);
                if (bU) myTank.setDir(TkDir.UP);
                if (bR) myTank.setDir(TkDir.RIGHT);
                if (bD) myTank.setDir(TkDir.DOWN);
            }
        }

    }

}
