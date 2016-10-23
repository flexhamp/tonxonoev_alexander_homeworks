package ru.homeworks.jmm.hw1;

import java.util.concurrent.Callable;

/**
 * Created by Alexander on 02.10.2016.
 */
public class Task<T> {
    private final Callable<? extends T> callable;
    private static final Object object=new Object();
    private volatile TaskException taskException;
    private volatile T result;

    public Task(Callable<? extends T> callable){
        this.callable=callable;
    }
    public T get() throws TaskException {
        if (taskException!=null) { //исключение уже было сгенерировано в другом потоке.
            System.out.println("Исключение было в другом потоке");
            throw taskException;
        }
        if (result!=null) {   // happens before освобождения монитора object
            System.out.println("Высчитывать не пришлось. "+Thread.currentThread().getName());
            return result;
        }
        synchronized (object){
            if (taskException!=null) {  // double check
                System.out.println("Исключение было в другом потоке");
                throw taskException;
            }
            if (result!=null) {
                System.out.println("Высчитывать не пришлось.(вторая проверка) "+Thread.currentThread().getName());
                return result;
            }
            try {
                result=callable.call();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                taskException=new TaskException(e);
            }
            return result;
        }

    }
}
