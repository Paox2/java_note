package com.threadPool;

public interface rejectPolicy<T> {
    void reject(BockingQueue<T> queue, T task);
}
