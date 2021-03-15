package com.liuyao.design_patterns.state;

public class MainState {

    /**
     * 状态模式
     *  根据状态决定行为
     *  扩展state方法 state方法只有一个 不需要用
     *
     *  书例子：
     *      TCPConnection open() close() acknowledge()
     */
    public static void main(String[] args) {
        CarState carState = new CarClosedState();

        carState.closeDoor();
        carState.openDoor();
        carState.runCar();
        carState.stopCar();

    }
}
