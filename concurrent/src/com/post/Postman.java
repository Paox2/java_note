package com.post;

import java.util.logging.Logger;

public class Postman extends Thread{
    private int id;
    private String mail;
    private static Logger log = Logger.getLogger("com.post");

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardObject guardObject = Mailboxes.getGuardObject(id);
        log.info("start delivery id: " + id + ", content: " + mail);
        guardObject.complete(mail);
    }
}