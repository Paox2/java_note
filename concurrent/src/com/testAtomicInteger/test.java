package com.testAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class test {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);

        System.out.println(i.incrementAndGet()); // 1 1
        System.out.println(i.getAndIncrement()); // 1 2

        System.out.println(i.getAndAdd(5)); // 2 7
        System.out.println(i.addAndGet(5)); // 12 12

        System.out.println(i.updateAndGet(x -> x * 10)); // 120 120
        System.out.println(i.getAndUpdate(x -> x * 10)); // 120 1200

        System.out.println(updateAndGet(i, x->x/10)); // 120 120

        System.out.println(i.get()); // 120
    }

    public static int updateAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while(true) {
          int pre = i.get();
          int after = operator.applyAsInt(pre);
          if (i.compareAndSet(pre, after)){
              return after;
          }
      }
    }
}
