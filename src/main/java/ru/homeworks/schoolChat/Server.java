package ru.homeworks.schoolChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Alexander on 14.10.2016.
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private static ConcurrentMap<String, LinkedList<String>> messagesMap = new ConcurrentHashMap<>();
    private static CopyOnWriteArrayList<ServerWorker> clientsList = new CopyOnWriteArrayList<>();


    // данный класс необходим для работы в потоке с клиентом.
    private class ServerWorker implements Runnable {
        private final Socket socket;
        private final DataInputStream inputStream;
        private final DataOutputStream outputStream;
        private String login;
        private final String helloMessage = "Привет! Введи свой логин.";

        public String getLogin() {
            return login;
        }



        public ServerWorker(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
            this.socket = socket;

            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        private void sendMessage(String msg) throws IOException {
            outputStream.writeUTF(msg);
            outputStream.flush();
        }


        public String systemMessage(String message) {
            return "[SYSTEM] " + message;
        }

        public String userMessage(String login, String string) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String result = login + "[" + dateFormat.format(date) + "]:>>" + string;
            return result;
        }

        public String fromClientMessage(String message) throws IOException {
            if (!message.isEmpty() || !message.equals("") || !message.equals(" ")
                    || !message.equals("\n") || message.equals(" \n") || !message.equals("\0")) {


                String login = message.substring(message.indexOf("[") + 1, message.indexOf("]"));
                String msg = message.substring(message.indexOf("]") + 1);


                // обработаем сообщение пользователю
                if (!msg.isEmpty()) {
                    if (msg.indexOf(">>") >= 0) {
                        String receiver = msg.substring(0, msg.indexOf(">>"));
                        msg = msg.substring(msg.indexOf(">>") + 2);

                        //добавляем в message map


                        if (messagesMap.containsKey(receiver)) {
                            LinkedList<String> list = messagesMap.get(receiver);
                            list.add(userMessage(login, msg));
                            messagesMap.put(receiver, list);
                        } else {
                            outputStream.writeUTF(systemMessage("Пользователь " + receiver + " не найден."));
                        }

                    } else {
                        return msg;
                    }

                }

            }
            return message;
        }

        @Override
        public void run() {
            String message = "\n";
            LOGGER.info(this.socket.getInetAddress() + " подключен");
            try {

                // отправим приветствие клиенту
                outputStream.writeUTF(systemMessage(helloMessage));
                outputStream.flush();

                // авторизация
                login = inputStream.readUTF();
                if (messagesMap.containsKey(login) && clientsList.contains(this)) {
                    LOGGER.info(login + " теперь Online");
                } else {
                    messagesMap.put(login, new LinkedList<>());
                    LOGGER.info("Новый пользователь " + login + " теперь Online!");
                    clientsList.add(this);
                }

                // пуш уведомление о подключении нового пользователя.
                for (ServerWorker sw : clientsList) {
                    sw.sendMessage(systemMessage("Новый пользователь " + login + " теперь Online!"));
                }

                // пока не получим команду !exit будем слушать сообщения от пользователя.


                String code = "";
                while (!code.equals("!exit")) {
                    message = inputStream.readUTF();

                    code = fromClientMessage(message);

                    switch (code) {
                        case "!get": {
                            LinkedList<String> list = messagesMap.get(login);
                            if (!list.isEmpty()) {
                                for (String m :
                                        list) {
                                    this.sendMessage(m);
                                }
                            } else {
                                this.sendMessage(systemMessage("Нет новых сообщений"));
                            }
                        }
                        case "!exit": {

                        }
                        break;
                        default: {
                            if (code.indexOf(">>") == -1) {
                                this.sendMessage(systemMessage("Для отправки сообщения пользователю используйте шаблон user>>message\n") +
                                        systemMessage("Для получения непрочитанных сообщений, используйте !get\n") +
                                        systemMessage("Для выхода напечатайте !exit"));
                            }
                        }
                        break;
                    }

                }

                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }


    }


    public void startServer() throws IOException {

        LOGGER.info("Сервер начал прослушивать порт.");
        try (ServerSocket serverSocket = new ServerSocket(8199)
        ) {
            while (true) {
                Socket socket = serverSocket.accept();

                InputStream in=socket.getInputStream();
                OutputStream out=socket.getOutputStream();



                ServerWorker serverWorker = new ServerWorker(socket, new DataInputStream(in), new DataOutputStream(out));

                Thread thread = new Thread(serverWorker);
                thread.start();


            }
        }


    }


    public static void main(String[] args) {
        try {
            new Server().startServer();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
