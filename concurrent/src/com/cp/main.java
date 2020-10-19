package com.cp;

import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++){
            int id = i;
            new Thread(()->{
                queue.put(new Message(id, "value" + id));
            }, "producer" + i).start();
        }

        new Thread(()->{
            while(true) {
                try {
                    sleep(1000);
                    Message message = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }
}
