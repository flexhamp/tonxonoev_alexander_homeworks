package ru.homeworks.jmm.hw2.threadpool;


public class Threadpool<T extends Runnable> {
    PoolHandler<T>[] poolHandlers;
    private final int numberOfThreads;
    private TaskSyncQueue<T> taskSyncQueue = new TaskSyncQueue<>();
    private int interruptedTasksCount=0;

    private static final Object lock=new Object();

    //
    public Threadpool(int numberOfThreads) {

        if (numberOfThreads < 1) {
            this.numberOfThreads = 1; // если вдруг количество поток указано не верно, будет созадваться один поток.
        } else {
            this.numberOfThreads = numberOfThreads;
        }
        poolHandlers = new PoolHandler[this.numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            poolHandlers[i] = new PoolHandler(taskSyncQueue);
            poolHandlers[i].start();
        }
    }

    // добавление задачи в очередь
    public void addTask(T t) throws Exception {
        if (t != null) {
            taskSyncQueue.addLast(t);
        } else {
            throw new Exception("Задача для потока не определена.");
        }
    }


    // остановка тред пула
    public void stop() {
        for (PoolHandler<T> poolHandler : poolHandlers) {
            poolHandler.interrupt();
        }
    }

    // количество выполненых задач
    public int getExecutedTasksCount() {
        int res = 0;
        for (int i = 0; i < this.numberOfThreads; i++) {
            res += poolHandlers[i].getExecuteTasksCount();
        }
        return res;
    }

    // количество задач, в которых выброшено исключение
    public int getFailedTasksCount() {
        int res=0;
        for (int i = 0; i < this.numberOfThreads; i++) {
            res+=poolHandlers[i].getFailedTasksCount();
        }
        return res;
    }

    public void interrupt(){
        synchronized (lock){
            interruptedTasksCount=taskSyncQueue.removeAll();
        }
        this.stop();
    }
    // колличество невыполненных задач
    public int getInterruptedTasksCount(){
        synchronized (lock){
            return interruptedTasksCount;
        }
    }
}
