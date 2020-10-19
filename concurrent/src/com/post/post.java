package com.post;


import static java.lang.Thread.sleep;

public class post {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++){
            new People().start();
        }
        sleep(1000);
        for (Integer id : Mailboxes.getIDs()) {
            new Postman(id, "content" + id).start();
        }
    }
}