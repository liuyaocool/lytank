package com.liuyao.design_patterns.state;

public class CarClosedState extends CarState{
    @Override
    public void openDoor() {
        System.out.println("closed openDoor: ok");
    }

    @Override
    public void closeDoor() {
        System.out.println("closed closeDoor: no");
    }

    @Override
    public void runCar() {
        System.out.println("closed runCar: ok");
    }

    @Override
    public void stopCar() {
        System.out.println("closed stopCar: ok");
    }
}
