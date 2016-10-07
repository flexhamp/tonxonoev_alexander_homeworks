package ru.homeworks.soothsayer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Alexander on 01.10.2016.
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        int valueFromServer;
        System.out.println("Загадайте число.");
        Scanner scanner=new Scanner(System.in);
        valueFromServer=scanner.nextInt(); // загаданное число

        ServerSocket serverSocket=new ServerSocket(8199);
        LOGGER.info("Server have started!");
        while (true){
            Socket socket=serverSocket.accept();
            Thread thread=new Thread(new ClientWorker(valueFromServer, socket));
            thread.start();
        }

    }
}
