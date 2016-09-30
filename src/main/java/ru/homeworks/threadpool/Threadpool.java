package ru.homeworks.threadpool;


public class Threadpool<T extends Runnable> {
    private PoolHandler<T>[] poolHandlers;
    private int numberOfThreads;
    private TaskSyncQueue<T> taskSyncQueue = new TaskSyncQueue<>();


    // TODO: 25.09.2016 наложить условия на numberOfThreads
    public Threadpool(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        if (numberOfThreads<1){
            this.numberOfThreads=1; // если вдруг количество поток указано не верно, будет созадваться один поток.
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


}
