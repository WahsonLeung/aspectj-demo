package com.wahson.aspects;

/**
 * Created by WahsonLeung on 2022/4/26
 */
public aspect MyAspect {
    final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account acc):
        call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    before(int amount, Account acc): callWithDraw(amount, acc) {
        System.out.println("Before withdraw " + amount + " from " + acc);
    }

    boolean around(int amount, Account acc):
        callWithDraw(amount, acc) {
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance): callWithDraw(amount, balance) {
        System.out.println("After withdraw " + amount + ": leave: " + balance.balance);
    }
}
