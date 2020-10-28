package com.threadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class BockingQueue<T>{
    private static Logger log = Logger.getLogger("com.threadPool");
    // task queue
    private Deque<T> queue = new ArrayDeque<>();
    // lock
    private ReentrantLock lock = new ReentrantLock();
    // producer condition
    private Condition fullWaitSet = lock.newCondition();
    // consumer condition
    private Condition emptyWaitSet = lock.newCondition();
    private int capcity;

    public BockingQueue(int capcity) {
        this.capcity = capcity;
    }

    // take() with limited time
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while(queue.isEmpty()) {
                try {
                    if(nanos <= 0) {
                        return null;
                    }
                    // function return remaining time
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void put(T task) {
        lock.lock();
        try {
            while(queue.size() == capcity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("add to task list: "+task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    // put() with limited time
    public boolean offer(T task, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while(queue.size() == capcity) {
                try {
                    log.info("wait add to task list: "+task);
                    if(nanos <= 0){
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(rejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if(queue.size() == capcity) {
                rejectPolicy.reject(this, task);
            } else {
                log.info("add to task list: "+task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
