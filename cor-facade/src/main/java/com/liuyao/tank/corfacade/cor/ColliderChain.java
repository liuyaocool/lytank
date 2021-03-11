package com.liuyao.tank.corfacade.cor;

import com.liuyao.tank.corfacade.Entity.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliders = new ArrayList<>();

    public ColliderChain() {
        add(new TankBulletCollider());
        add(new TankTankCollider());
    }

    public void add(Collider o) {
        colliders.add(o);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).collide(o1, o2)) {
                break;
            }
        }
        return true;
    }
}
