package com.eat;

import java.util.logging.Logger;

public class Philosopher extends Thread{
    String name;
    Chopstick left;
    Chopstick right;
    private static Logger log = Logger.getLogger("com.eat");

    public Philosopher(String name, Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    public void run() {
        while(true){
            if(left.tryLock()){
                try {
                    if(right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
        log.info("eating...");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
