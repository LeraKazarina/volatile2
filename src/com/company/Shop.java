package com.company;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class Shop implements Runnable {
    private LongAdder balance;
    private final List<Integer> localBalance;
    private final int VALUES_LIMIT = 1_000_000;
    private final int LOW_LIMIT = 0;
    private final int UPPER_LIMIT = 100;
    private String title;

    public Shop(String title, LongAdder balance) {
        this.localBalance = new Random().ints(LOW_LIMIT, UPPER_LIMIT)
                .limit(VALUES_LIMIT)
                .boxed()
                .collect(Collectors.toList());
        this.title = title;
        this.balance = balance;
    }

    public List<Integer> getLocalBalance() {
        return localBalance;
    }

    @Override
    public void run() {
        getLocalBalance().forEach(balance::add);
    }
}
