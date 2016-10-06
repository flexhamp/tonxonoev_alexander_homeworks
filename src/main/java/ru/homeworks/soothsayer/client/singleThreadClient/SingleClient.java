package ru.homeworks.soothsayer.client.singleThreadClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Alexander on 06.10.2016.
 */
public class SingleClient {
    private static final Logger LOGGER= LoggerFactory.getLogger(SingleClient.class);
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8199);
             InputStream in=socket.getInputStream();
             OutputStream out=socket.getOutputStream();
             DataInputStream dataInputStream=new DataInputStream(in);
             DataOutputStream dataOutputStream=new DataOutputStream(out);
        ){
            int value;
            boolean flag=true;
            LOGGER.info(dataInputStream.readUTF()+"["+Thread.currentThread().getName()+"]");


                System.out.println("Угадайте число!");
                Scanner scanner=new Scanner(System.in);
                value=scanner.nextInt();

                dataOutputStream.writeInt(value);
                if (dataInputStream.readBoolean()){
                    System.out.println("Число угадано! Число было "+value);
                    LOGGER.info("Число: "+value);

                } else {
                    System.out.println("Число не угадано!");
                }




        } catch (IOException e) {
            LOGGER.error("[Exception]"+e.getMessage());
        }
    }
}
