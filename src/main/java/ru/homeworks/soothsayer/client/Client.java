package ru.homeworks.soothsayer.client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by Alexander on 02.10.2016.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService=newFixedThreadPool(100);
        ClientRunnable clientRunnable=new ClientRunnable();
        for (int i = 0; i < 100; i++) {
            executorService.submit(clientRunnable);
        }
        executorService.shutdown();
    }
}
