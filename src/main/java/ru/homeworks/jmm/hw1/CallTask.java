package ru.homeworks.jmm.hw1;

import java.util.concurrent.Callable;

/**
 * Created by Alexander on 03.10.2016.
 */
public class CallTask implements Callable<Integer> {
    private final int a;
    public CallTask() {
        a=0;
    }

    public CallTask(int a) {
        this.a = a;
    }

    @Override
    public Integer call() throws Exception {
        int b=a+9;
        System.out.printf("Thread: %s return value: %d\n", Thread.currentThread().getName(),b);
        return b;
    }
}
