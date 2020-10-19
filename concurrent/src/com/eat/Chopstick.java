package com.eat;

import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock {
    String name;
    public Chopstick(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
