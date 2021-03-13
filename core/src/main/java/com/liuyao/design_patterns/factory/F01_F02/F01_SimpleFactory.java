package com.liuyao.design_patterns.factory.F01_F02;

/**
 * 简单工长
 * 可扩展性不好
 */
public class F01_SimpleFactory {

    public ICar createCar(){
        // before Processing
        return new ICar();
    }

    public IPlane createPlane(){
        return new IPlane();
    }
}
