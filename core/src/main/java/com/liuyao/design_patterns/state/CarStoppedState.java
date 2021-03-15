package com.liuyao.design_patterns.state;

public class CarStoppedState extends CarState{
    @Override
    public void openDoor() {
        System.out.println("opened openDoor: ok");
    }

    @Override
    public void closeDoor() {
        System.out.println("opened closeDoor: no");
    }

    @Override
    public void runCar() {
        System.out.println("opened runCar: ok");
    }

    @Override
    public void stopCar() {
        System.out.println("opened stopCar: no");
    }
}
