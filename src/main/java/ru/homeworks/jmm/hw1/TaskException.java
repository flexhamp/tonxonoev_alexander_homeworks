package ru.homeworks.jmm.hw1;

/**
 * Created by Alexander on 03.10.2016.
 */
public class TaskException extends Exception {

    public TaskException(Exception e){
        super(e.getMessage());
    }
}
