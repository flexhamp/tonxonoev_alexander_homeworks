package ru.homeworks.jmm.hw1;

/**
 * Created by Alexander on 03.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        CallTask callTask=new CallTask(25);
        Task<Integer> task=new Task<>(callTask);

        for (int i = 0; i < 5; i++) {
            //Thread thread=new Thread()
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        System.out.println(Thread.currentThread().getName()+" вернул "+task.get());
                    } catch (TaskException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
