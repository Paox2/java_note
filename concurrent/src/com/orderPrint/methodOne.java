package com.orderPrint;

import java.util.logging.Logger;

public class methodOne {
    static final Object lock = new Object();
    static boolean t2runned = false;
    private static Logger log = Logger.getLogger("com.orderPrint");

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            synchronized (lock) {
                while (!t2runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("t1");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                log.info("t2");
                t2runned = true;
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
