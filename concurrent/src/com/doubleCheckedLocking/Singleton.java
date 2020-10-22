package com.doubleCheckedLocking;

import java.io.Serializable;

/*
*   // also no thread safety problem
*   enum Singleton {
*       INSTANCE;
*   }
*/

public final class Singleton implements Serializable {
    private Singleton() {}

    // volatile avoid intruction re-order by CPU
    // if initial instance here, use final and it is safe-thread because static variable initial when object initial
    private volatile static Singleton INSTANCE = null;

    public static Singleton genInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public Object readResolve() {
        return INSTANCE;
    }
}

/*
*  public final class Singleton {
*       private Singleton() {}
*
*       private static class LazyHolder {
*           static final Singleton INSTANCE = new Singleton();
*       }
*
*       public static Singleton getInstance() {
*           return LazyHolder.INSTANCE;
*       }
*   }
* */
