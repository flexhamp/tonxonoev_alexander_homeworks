package ru.homeworks.multimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1) Нужно сделать структуру данных, которая хранит таблицу всех имеющихся грузовиков. Из этой структуры надо иметь
 * возможность доставать все грузовики по их типу.
 */

public class TruckMap {

    private Map<String, List<Truck>> typeMap;

    public TruckMap() {
        typeMap = new HashMap<>();
    }

    // конструктор как в примере с truckDao
    public TruckMap(TruckDao truckDao) {
        typeMap = new HashMap<>();
        List<Truck> trucks = truckDao.toList();

        for (Truck truck : trucks) {

            List<Truck> truckList = typeMap.get(truck.getType());
            if (truckList != null) {
                truckList.add(truck);
                typeMap.put(truck.getType(), truckList);
            } else {
                truckList = new ArrayList<>();
                truckList.add(truck);
                typeMap.put(truck.getType(), truckList);
            }
        }
    }


    // добавление элементов в структуру
    public void put(Truck truck) {

        List<Truck> truckList = typeMap.get(truck.getType());
        if (truckList != null) {
            truckList.add(truck);
            typeMap.put(truck.getType(), truckList);
        } else {
            truckList = new ArrayList<>();
            truckList.add(truck);
            typeMap.put(truck.getType(), truckList);
        }
    }

    // получение элемнтов из стуктуры
    public List<Truck> get(String key) {
        List<Truck> truckList = typeMap.get(key);
        return truckList;
    }

    @Override
    public String toString() {
        String tmp = "[";
        for (Map.Entry<String, List<Truck>> entry : typeMap.entrySet()) {
            tmp += "Key: " + entry.getKey() + " Values{";
            for (Truck value : entry.getValue()) {
                tmp += value + " ";
            }
            tmp += "} ";
        }
        tmp += "]";
        return tmp;
    }
}
