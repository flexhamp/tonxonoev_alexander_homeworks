package ru.homeworks.soothsayer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Created by Alexander on 05.10.2016.
 */
public class ClientRunnable implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(ClientRunnable.class);
    private static final Object object=new Object();
    private int value=0;
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 8199);
             InputStream in=socket.getInputStream();
             OutputStream out=socket.getOutputStream();
             DataInputStream dataInputStream=new DataInputStream(in);
             DataOutputStream dataOutputStream=new DataOutputStream(out);
        ){

            LOGGER.info(dataInputStream.readUTF()+"["+Thread.currentThread().getName()+"]");

            synchronized (object){
                value++;
                dataOutputStream.writeInt(value);
                if (dataInputStream.readBoolean()){
                    LOGGER.info("Число: "+value);
                }
            }


        } catch (IOException e) {
            LOGGER.error("[Exception]"+e.getMessage());
        }
    }
}
