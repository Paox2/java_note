package com.cp;

import java.util.LinkedList;
import java.util.logging.Logger;

public class MessageQueue {
    private LinkedList<Message> list = new LinkedList<>();
    private int capcity;
    private static Logger log = Logger.getLogger("com.post");

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    public Message take() {
        //check if list is empty
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.info("no product, wait to produce");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            log.info("Have been consumed: " + message);
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        //check if list is full
        synchronized (list) {
           while (list.size() == capcity) {
               try {
                   log.info("product is full, wait to consume");
                   list.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           list.addLast(message);
           log.info("Have produced: " + message);
           list.notifyAll();
        }
    }
}
