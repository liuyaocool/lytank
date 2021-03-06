package com.liuyao.tank.corfacade.cor;

import com.liuyao.tank.corfacade.entity.Bullet;
import com.liuyao.tank.corfacade.entity.Explode;
import com.liuyao.tank.corfacade.entity.GameObject;
import com.liuyao.tank.corfacade.entity.Tank;
import com.liuyao.tank.corfacade.GameModel;

public class TankBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank t = (Tank) o1;
            Bullet b = (Bullet) o2;
            if (t.group != b.group && t.rect.intersects(b.rect)){
                t.die();
                b.die();
                GameModel.getInstance().add(new Explode(t.x, t.y));
                return true;
            }
        } else if (o1 instanceof Bullet && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return false;
    }
}
