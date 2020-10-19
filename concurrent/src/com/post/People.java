package com.post;

import java.util.logging.Logger;

public class People extends Thread{
    private static Logger log = Logger.getLogger("com.post");

    @Override
    public void run() {
        GuardObject guardObject = Mailboxes.createGuardObject();
        log.info("try to get email: " + guardObject.getId());
        Object mail = guardObject.get(5000);
        log.info("get email id:" + guardObject.getId() + ", content: " + mail);
    }
}