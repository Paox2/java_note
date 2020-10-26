package com.atomicAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class testAtomicInteger {
    public static void main(String[] args){
        Account account = new AccountCas(10000);
        Account.demo(account);

        Account account2 = new AccountUnsafe(10000);
        Account.demo(account2);
    }
}

class AccountCas implements Account {
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return null;
    }

    @Override
    public void withdraw(Integer amount) {
//        while(true){
//            int pre = balance.get();
//            int after = pre - amount;
//            if (balance.compareAndSet(pre, after)){
//                break;
//            }
//        }
        balance.getAndAdd(-1 * amount);
    }
}

class AccountUnsafe implements Account {
    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

interface Account {
    Integer getBalance();
    void withdraw(Integer amount);

    static void demo(Account account){
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            ts.add(new Thread(()->{
                account.withdraw(10);
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
        System.out.println(account.getBalance() + "cost: " +
                (end-start)/1000_000 + " ms");
    }
}
