package com.alternatePrint;

// park unpark

import java.util.concurrent.locks.LockSupport;


public class methodThree {
    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args){
        parkUnpark pu = new parkUnpark(5);
        new Thread(()->{
            pu.print("a  ", t2);
        },"t1").start();

        new Thread(()->{
            pu.print("b  ", t3);
        },"t2").start();

        new Thread(()->{
            pu.print("b  ", t1);
        },"t3").start();
    }
}

class parkUnpark {
    private int loopNumber;

    parkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}
