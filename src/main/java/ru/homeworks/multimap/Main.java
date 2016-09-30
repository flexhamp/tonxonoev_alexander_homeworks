package ru.homeworks.multimap;



/**
 * Created by Alexander on 22.09.2016.
 */
public class Main {
    public static void main(String[] args) {

        // пример работы MultiMap

        MultiMap<Integer, String> multiMap=new MultiMap<>();

        multiMap.put(0, "Нулевой");
        multiMap.put(1, "Первый");
        multiMap.put(2, "Второй");
        multiMap.put(3, "Третий");
        multiMap.put(4, "Четвертый");
        multiMap.put(5, "Пятый");
        multiMap.put(6, "Шестой");
        multiMap.put(7, "Седьмой");
        multiMap.put(8, "Восьмой");
        multiMap.put(9, "Девятый");

        multiMap.put(4, "Еще четвертый");
        multiMap.put(4, " И еще");

        System.out.println(multiMap);

        // пример работы с TruckMap

        TruckDao truckDao = new TruckDaoImpl();
        TruckMap map = new TruckMap(truckDao);
        System.out.println(map);

    }
}
