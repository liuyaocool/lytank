package com.liuyao.tank.corfacade.cor;

import com.liuyao.tank.core.PropertyMgr;
import com.liuyao.tank.corfacade.Entity.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliders = new ArrayList<>();

    public ColliderChain() {
        String[] colliders = PropertyMgr.getProperty("colliders").split(",");
        for (int i = 0; i < colliders.length; i++) {
            // 反射 添加责任链
            try {
                Class<?> aClass = Class.forName(colliders[i]);
                Object o = aClass.getConstructor().newInstance();
                if (o instanceof Collider) {
                    add((Collider) o);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
//
//        add(new TankBulletCollider());
//        add(new TankTankCollider());
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
