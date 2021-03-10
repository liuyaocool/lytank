package com.liuyao.tank.Strategy;

import com.liuyao.tank.factory.FacTank;

/**
 * 原类名为 BulletStrategy
 *  思想错误
 *  这不是子弹的策略 而是发射子弹的策略
 */
public interface FacFireStrategy {
    void fire(FacTank tank);
}
