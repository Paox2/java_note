package com.monitorTh;

import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class test {

}

class TwoPhaseTermination {
    private Thread monitorThread;   // moniter thread
    private volatile boolean stop;  // stop signal
    private boolean starting = false;
    private static Logger log = Logger.getLogger("com.monitorTh");

    public void start() {
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
            monitorThread =new Thread(() -> {
                while(true) {
                    Thread current = Thread.currentThread();
                    if(stop) {
                        log.info("Deal with sth");
                    }
                    try {
                        sleep(1);
                        log.info("monitoring");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            monitorThread.start();
        }
    }

    public void stop() {
        stop = true;
        monitorThread.interrupt();;
    }
}