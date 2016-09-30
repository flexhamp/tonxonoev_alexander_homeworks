package ru.homeworks.threadpool;

public class PoolHandler<T extends Runnable> extends Thread {
    private TaskSyncQueue<T> taskSyncQueue;

    public PoolHandler(TaskSyncQueue<T> taskSyncQueue) {
        this.taskSyncQueue = taskSyncQueue;
    }

    @Override
    public void run() {
        Runnable work;
        while (true){
            if (Thread.currentThread().isInterrupted()){
                break;  // если поток попросили завершиться, то откликаемся на эту просьбу
            }
            // будем ожидать задачи из очереди
            try {
                work=(Runnable)taskSyncQueue.getFirst();
                work.run();
            } catch (InterruptedException e) {
                break; // прекратить бесконечный цикл, если словили interrupted exception
            }
        }

    }
}
