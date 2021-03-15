package com.liuyao.design_patterns.state;

public abstract class CarState {
    public abstract void openDoor();
    public abstract void closeDoor();
    public abstract void runCar();
    public abstract void stopCar();
}
