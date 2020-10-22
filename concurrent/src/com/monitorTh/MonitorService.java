package com.monitorTh;

import java.util.logging.Logger;

public class MonitorService {
    private Thread monitorThread;   // moniter thread
    private volatile boolean stop;  // stop signal
    private volatile boolean starting = false;
    private static Logger log = Logger.getLogger("com.monitorTh");

    public void start() {
        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }
        monitorThread = new Thread(() -> {
            while (!stop) {
                report();
                sleep(2);
            }
            log.info("Moniter thread stop");
            starting = false;
        });

        stop = false;
        log.info("moniter thread start");
    }

    private void report() {}

    private void sleep(long seconds) {}

    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }
}
