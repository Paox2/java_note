package com.atomicAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class testAtomicReference {
    public static void main(String[] args) {
        DecimalAccount.demo(new DemicalAccountCas(new BigDecimal(10000)));
    }
}


class DemicalAccountCas implements DecimalAccount {
    private AtomicReference<BigDecimal> balance;

    public DemicalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while(true) {
            BigDecimal pre = balance.get();
            BigDecimal after = pre.subtract(amount);
            if (balance.compareAndSet(pre, after)){
                break;
            }

        }
    }
}

interface DecimalAccount {
    BigDecimal getBalance();
    void withdraw(BigDecimal amount);

    static void demo(DemicalAccountCas account){
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            ts.add(new Thread(()->{
                account.withdraw(BigDecimal.TEN);
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " +
                (end-start)/1000_000 + " ms");
    }
}
