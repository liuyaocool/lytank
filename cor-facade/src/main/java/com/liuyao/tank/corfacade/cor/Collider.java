package com.liuyao.tank.corfacade.cor;

import com.liuyao.tank.corfacade.Entity.GameObject;

public interface Collider {
    // 撞上 返回true
    boolean collide(GameObject o1, GameObject o2);
}
