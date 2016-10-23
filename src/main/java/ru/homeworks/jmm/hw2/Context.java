package ru.homeworks.jmm.hw2;

/**
 * Created by Alexander on 04.10.2016.
 */
public interface Context {
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
}
