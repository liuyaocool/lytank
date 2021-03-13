package com.liuyao.tank.corfacade.cor;

import com.liuyao.tank.corfacade.entity.GameObject;
import com.liuyao.tank.corfacade.entity.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank
                && o1.rect.intersects(o2.rect)) {
            ((Tank) o1).back();
            ((Tank) o2).back();
            return true;
        }
        return false;
    }
}
