package ru.homeworks.threadpool;

import java.util.LinkedList;
import java.util.Queue;


// Очередь заданий. Должна принимать задачи, то есть любой класс реализующий Runnable().
public class TaskSyncQueue<T extends Runnable> {
    private Queue<T> queue=new LinkedList<>();


    //  Добавление в очередь(должно быть синхронизованно)
    public void addLast(T task){
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }
    //  Извлечение из очерди(так же должно быть синхронизовано)
    public T getFirst() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            return queue.remove();
        }
    }

    // Проверка на наличие элементов
    public boolean isEmpty(){
        boolean result;
        synchronized (queue){
            result=queue.isEmpty();
        }
        return result;
    }
}
