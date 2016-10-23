package ru.homeworks.jmm.hw2.threadpool;

public class PoolHandler<T extends Runnable> extends Thread {
    private TaskSyncQueue<T> taskSyncQueue;
    private int failedTasksCount=0;  //каждый поток, который ждет задачи теперь
    private int executeTasksCount=0; //будет подсчитывать количество успешно и неуспешно выполненных тасков.

    public int getFailedTasksCount() {
        return failedTasksCount;
    }

    public int getExecuteTasksCount() {
        return executeTasksCount;
    }

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
                executeTasksCount++;
            }
            catch (InterruptedException e) {
                break; // прекратить бесконечный цикл, если словили interrupted exception
            }
            catch (Exception e){  // если возникло исключение, то под
                System.out.println(e.getMessage());
                failedTasksCount++;

                continue;
            }

        }

    }
}
