package com.connectionPool;

import java.sql.Connection;

import static java.lang.Thread.sleep;

public class test {
    public static void main(String[] args) {
        Pool pool = new Pool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                Connection conn = pool.borrow();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pool.back(conn);
            }).start();
        }
    }
}
