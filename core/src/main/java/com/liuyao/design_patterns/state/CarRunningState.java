package com.liuyao.design_patterns.state;

public class CarRunningState extends CarState{
    @Override
    public void openDoor() {
        System.out.println("opened openDoor: no");
    }

    @Override
    public void closeDoor() {
        System.out.println("opened closeDoor: no");
    }

    @Override
    public void runCar() {
        System.out.println("opened runCar: no");
    }

    @Override
    public void stopCar() {
        System.out.println("opened stopCar: ok");
    }
}
