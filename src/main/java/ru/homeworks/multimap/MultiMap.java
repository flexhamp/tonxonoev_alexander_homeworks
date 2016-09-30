package ru.homeworks.multimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2) Обобщить струтуру. По любому ключу хранить объекты любого типа.
 */
public class MultiMap<K, V> {
    private Map<K, List<V>> multiMap;

    // констркутор
    public MultiMap() {
        multiMap = new HashMap<>();
    }

    // добавление в коллекцию
    public void put(K key, V value) {
        List<V> list = multiMap.get(key);
        if (list != null) {
            list.add(value);
            multiMap.put(key, list);
        } else {
            list = new ArrayList<V>();
            list.add(value);
            multiMap.put(key, list);
        }
    }

    // получение элементов из коллекции
    public List<V> get(K key) {
        return multiMap.get(key);
    }

    @Override
    public String toString() {
        String tmp="[";
        for (Map.Entry<K, List<V>> entry: multiMap.entrySet()){
            tmp+="Key: "+entry.getKey()+" Values{";
            for (V value: entry.getValue()) {
                 tmp+=value.toString()+" ";
            }
            tmp+="} ";
        }
        tmp+="]";
        return  tmp;
    }
}
