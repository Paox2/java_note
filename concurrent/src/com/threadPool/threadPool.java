package com.threadPool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

// include set of threads and blocking queue
public class threadPool {
    private static Logger log = Logger.getLogger("com.threadPool");

    private BockingQueue<Runnable> taskQueue;
    // set of threads
    private HashSet<Worker> workers = new HashSet<>();
    private int coreSize;
    // limit time to get task, if over then stop the thread
    private long timeout;
    private TimeUnit timeUnit;

    private rejectPolicy<Runnable> rejectPolicy;

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.info("new worker: "+worker+" task: "+task);
                workers.add(worker);
                worker.start();
            } else {
                log.info("queue is full, add to blocking queue: "+task);
                // 1. wait  2. wait with time   3. give up the task     4. exception    5. task-producer self execute task
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public threadPool(int coreSize, int timeout, TimeUnit timeUnit, int queueCapcity,rejectPolicy<Runnable> rejectPolicy) {
        this.timeout = timeout;
        this.coreSize = coreSize;
        this.timeUnit = timeUnit;
        this.taskQueue = new BockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while(task != null || (task = taskQueue.poll(timeout, timeUnit))!=null) {
            //while(task != null || (task = taskQueue.take())!=null) {
                try {
                    log.info("running task: "+task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.info("worker is removed: "+this);
                workers.remove(this);
            }
        }
    }
}
