package com.company;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private final int THREADS_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        App myApp = new App();
        ExecutorService executorService = Executors.newFixedThreadPool(myApp.THREADS_NUMBER);//создаём исполнитель
        LongAdder balance = new LongAdder(); //итоговый результат

        List<Shop> shops = Stream.of(new Shop("Магазин 1", balance),
                new Shop("Магазин2", balance),
                new Shop("Магазин3", balance))
                .collect(Collectors.toList());

        for (Shop shop : shops) {
            executorService.submit(shop);
        }

        executorService.shutdown();//упорядоченное завершение работы, при котором ранее отправленные задачи выполняются, а новые задачи не принимаются
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("\nРезультат: " + balance.sum());
    }
}
