package ru.homeworks.soothsayer.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

/**
 * Created by Alexander on 02.10.2016.
 */
public class ClientWorker implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(ClientWorker.class);
    private int valueFromServer;
    private Socket socket;
    public ClientWorker(int valueFromServer, Socket socket) {
        this.valueFromServer = valueFromServer;
        this.socket=socket;

    }

    @Override
    public void run() {
        int value;
        String helloMessage="Подключение к серверу выполнено.";

        try (InputStream in=socket.getInputStream();
             OutputStream out=socket.getOutputStream();
             DataInputStream dataInputStream=new DataInputStream(in);
             DataOutputStream dataOutputStream=new DataOutputStream(out)
             ){
            LOGGER.info("Подключен клиен. Время "+ LocalTime.now().getHour()+":"+
             +LocalTime.now().getMinute()+":"+LocalTime.now().getSecond()+"\n");

            dataOutputStream.writeUTF(helloMessage);

            value=dataInputStream.readInt();

            LOGGER.info("Число получено: "+value);

            if (value!=valueFromServer){
                LOGGER.info("Число не угадано.");
                dataOutputStream.writeBoolean(false);
            } else {
                LOGGER.info("Число было УГАДАНО!");
                dataOutputStream.writeBoolean(true);
            }

            } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

    }
}
