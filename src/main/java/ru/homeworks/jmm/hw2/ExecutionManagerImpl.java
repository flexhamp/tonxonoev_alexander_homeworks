package ru.homeworks.jmm.hw2;


import ru.homeworks.jmm.hw2.threadpool.Threadpool;

/**
 * Created by Alexander on 04.10.2016.
 */
public class ExecutionManagerImpl implements ExecutionManager {
    private Threadpool<Runnable> threadpool;
    private int threadCount;

    public ExecutionManagerImpl(int threadCount) {
        if (threadCount < 1) {
            throw new IllegalArgumentException("Потоков должно быть как минимум 1.");
        }
        this.threadCount = threadCount;
        threadpool = new Threadpool<>(this.threadCount);
    }

    private class ContextImpl implements Context {
        private final int tasksCount;

        public int getTasksCount() {
            return tasksCount;
        }

        public ContextImpl(int tasksCount) {
            this.tasksCount = tasksCount;
        }

        @Override
        public int getCompletedTaskCount() {
            return threadpool.getExecutedTasksCount();
        }

        @Override
        public int getFailedTaskCount() {
            return threadpool.getFailedTasksCount();
        }

        @Override
        public int getInterruptedTaskCount() {
            return threadpool.getInterruptedTasksCount();
        }

        @Override
        public void interrupt() {
            threadpool.interrupt();
        }

        @Override
        public boolean isFinished() {
            int tasks = getCompletedTaskCount() + getFailedTaskCount();
            if (this.tasksCount == tasks) {
                return true;
            } else {
                return false;
            }

        }
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Context context = new ContextImpl(tasks.length);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (!context.isFinished()){
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                if (context.getFailedTaskCount()==0||(context.getInterruptedTaskCount()==0))
                callback.run();

            }
        }).start();

        try {
            for (int i = 0; i < tasks.length; i++) {
                threadpool.addTask(tasks[i]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        return context;
    }

}
