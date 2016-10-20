package ru.homeworks.schoolChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Alexander on 14.10.2016.
 */
public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private static final String address = "localhost";

    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;


    public Client(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    private class ClientWorker implements Runnable {
        String msg;

        @Override
        public void run() {
            try {
                while (true) {
                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }
                    msg = inputStream.readUTF();
                    System.out.println(msg);
                }

            } catch (IOException e) {
                Thread.currentThread().interrupt();
                LOGGER.error(e.getMessage());
            }


        }
    }

    private void startClient() throws IOException {
        String login;
        String message = "";
        Thread listener = new Thread(new ClientWorker());
        try {

            // получим сообщения сервера

            listener.start();


            //получим логин с консоли

            Scanner scanner = new Scanner(System.in);
            login = scanner.next();
            while (login.length() == 0 || login.equals("\n")|| login.equals(" ")
                    ||login.indexOf("[")!=-1||login.indexOf("]")!=-1) {
                System.out.println("[ERROR] Логин не может быть пустым и содержать символы \'[]\'");
                login = scanner.next();
            }
            // отправим логин серверу
            outputStream.writeUTF(login);
            outputStream.flush();


            // отправляем сообщения серверу
            while (!message.equals("!exit") ) {
                message = scanner.nextLine();
                if (!message.equals(" ")  || !message.equals("\n")|| !message.equals("\0")
                        || !message.equals(" \n")||message.length()!=0|| !message.equals("\n")) {
                    outputStream.writeUTF("["+login+"]"+message);
                    outputStream.flush();
                }
            }


            listener.interrupt();
            System.out.println("Вы вышли из чата");
            System.exit(0);
        } catch (IOException e) {
            listener.interrupt();
            LOGGER.error(e.getMessage());
            System.exit(0);
        }


    }

    public static void main(String[] args) {
        try {
            Socket socket=new Socket("localhost", 8199);
            InputStream in=socket.getInputStream();
            OutputStream out=socket.getOutputStream();

            new Client(socket, new DataInputStream(in), new DataOutputStream(out)).startClient();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
