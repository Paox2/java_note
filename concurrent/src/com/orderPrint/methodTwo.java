package com.orderPrint;

import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;

public class methodTwo {
    static final Object lock = new Object();
    static boolean t2runned = false;
    private static Logger log = Logger.getLogger("com.orderPrint");

    public static void main(String[] args){
        Thread t1 = new Thread(()->{
            LockSupport.park();
            log.info("t1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.info("t2");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
