package com.liuyao.test;

import java.util.concurrent.locks.ReentrantLock;

public class TestAQS {

    public static void main(String[] args) {

        final ReentrantLock l = new ReentrantLock(false);

//        l.tryLock();
        l.lock();

        l.unlock();

    }
}
