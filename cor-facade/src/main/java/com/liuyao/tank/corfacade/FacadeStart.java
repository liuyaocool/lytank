package com.liuyao.tank.corfacade;

public class FacadeStart {

    /**
     * 门面 又叫 外观 facade 对外同一
     * 调停者 又叫 中介者 mediator 对内统一
     *  消息队列
     * 此二者可以是一个人
     *
     * 责任链
     * @param args
     */
    public static void main(String[] args) {
        // 窗口类
        TankFrame tf = new TankFrame();

        while (true) {
            try {
                Thread.sleep(50);
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }
}
