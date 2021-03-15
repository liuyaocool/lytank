package com.liuyao.design_patterns.state;

public class CarOpenedState extends CarState{
    @Override
    public void openDoor() {
        System.out.println("opened openDoor: no");
    }

    @Override
    public void closeDoor() {
        System.out.println("opened closeDoor: ok");
    }

    @Override
    public void runCar() {
        System.out.println("opened runCar: no");
    }

    @Override
    public void stopCar() {
        System.out.println("opened stopCar: no");
    }
}
