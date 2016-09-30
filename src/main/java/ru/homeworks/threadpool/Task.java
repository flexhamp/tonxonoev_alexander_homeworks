package ru.homeworks.threadpool;

public class Task implements Runnable {
    Double balance=0.0;
    @Override
    public void run() {

        synchronized (balance){
            balance+=100.0;
            System.out.printf("Поток %s добавил $100. balance=$%f.\n",Thread.currentThread().getName(), balance);
        }

    }
}
