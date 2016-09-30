package ru.homeworks.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class URLReader {
    public static String readContent(String url) throws IOException {
        String temp;
        URL site = new URL(url);
        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputStreamReader = new InputStreamReader(site.openStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp+"\n");
        }

        inputStreamReader.close();
        bufferedReader.close();
        return  stringBuffer.toString();
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String url;
        System.out.println("Введите URL:");
        while (true) {
            try {
                url = bufferedReader.readLine();
                String result = readContent(url);
                System.out.println(result);
                break;
            } catch (IOException e) {
                System.out.println("Ошибка, повторите ввод заново.");
                continue;
            }
        }
    }
}
