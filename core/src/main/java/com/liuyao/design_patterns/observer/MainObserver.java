package com.liuyao.design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class MainObserver {

    /**
     * 观察者模式 observer/listener/hook/callback
     *  被观察者 List<Observer>
     *  观察者（谁等待事件） Observer
     *  事件 Event
     * @param args
     */
    public static void main(String[] args) {

        Child child = new Child();

        child.warkUp();

    }
}

class Child {

    boolean cry = false;
    List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Sister());

    }

    public void warkUp() {
        this.cry = true;

        WakeUpEvent wakeup = new WakeUpEvent("wakeup", this);

        for (Observer o: observers) {
            o.action(wakeup);
        }

    }

}

interface Event<T> {
    T getSource();
}

class WakeUpEvent implements Event<Child> {
    long time;
    String loc;
    Child source;

    public WakeUpEvent(String loc, Child source) {
        this.time = System.currentTimeMillis();
        this.loc = loc;
        this.source = source;
    }

    @Override
    public Child getSource() {
        return source;
    }
}


interface Observer {
    void action(WakeUpEvent e);
}

class Dad implements Observer {

    @Override
    public void action(WakeUpEvent e) {
        System.out.println("dad observer");
    }
}

class Mum implements Observer {

    @Override
    public void action(WakeUpEvent e) {
        System.out.println("mum observer");
    }
}

class Sister implements Observer {

    @Override
    public void action(WakeUpEvent e) {
        System.out.println("Sister observer");
    }
}
