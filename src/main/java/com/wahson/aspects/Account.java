package com.wahson.aspects;

/**
 * Created by WahsonLeung on 2022/4/26
 */
public class Account {
    int balance = 20;

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
