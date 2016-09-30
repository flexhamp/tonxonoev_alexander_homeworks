package ru.homeworks.threadpool;


public class Main {
    public static void main(String[] args) {
        Threadpool<Task> threadpool = new Threadpool<>(25);
        Task task = new Task();
        try {

            for (int i = 0; i < 100; i++) {
                threadpool.addTask(task);
            }
            Thread.sleep(5000); // подождем 5 секунд
        } catch (Exception e) {
            System.out.println(e);
        }
        threadpool.stop();
        System.out.println("-----------");
        System.out.println("Balance="+task.balance);
    }
}
