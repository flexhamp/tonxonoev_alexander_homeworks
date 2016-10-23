package ru.homeworks.jmm.hw2;

/**
 * Created by Alexander on 04.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        final int count = 36;
        Context context;
        class Task  implements Runnable {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.printf("%s выполняет Task.run()\n", Thread.currentThread().getName());
            }
        }

        class Callback implements Runnable {
            @Override
            public void run() {
                System.out.println("Вызов callback.");
            }
        }
        ExecutionManager executorManager = new ExecutionManagerImpl(6);
        Runnable[] tasks = new Task[count];
        for (int i = 0; i < count; i++) {
            tasks[i] = new Task();
        }

        context = executorManager.execute(new Callback(), tasks);

        try {
            while (!context.isFinished()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        context.interrupt();
    }
}
