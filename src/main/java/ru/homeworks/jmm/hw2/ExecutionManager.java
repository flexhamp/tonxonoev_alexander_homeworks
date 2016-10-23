package ru.homeworks.jmm.hw2;

/**
 * Created by Alexander on 04.10.2016.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... task);
}
