package ru.homeworks.multimap;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexander on 22.09.2016.
 */
public class Application {
    private Map<Long, Truck> truckRegistry=new TreeMap<>();

    public Application(TruckDao truckDao) {
        List<Truck> list=truckDao.toList();
        for (Truck truck : list) {
            Truck previousValue=truckRegistry.put(truck.getId(), truck);
            if (previousValue!=null){
                throw new IllegalStateException("Two truck with same ID");
            }
        }
    }
    public Truck getTrackById(long truckId){
        Truck truck=truckRegistry.get(truckId);
        if (truck!=null){
            throw new IllegalStateException("Not found truck with ID: "+truckId);
        }
        return truck;
    }
    void viewTruck(){
        System.out.println(truckRegistry);
    }
    void viewTruckRegistry(){
        for (Map.Entry<Long, Truck> truckEntry: truckRegistry.entrySet()){
            System.out.println(truckEntry.getKey()+": "+truckEntry.getValue());
        }
    }

//    public static void main(String[] args) {
//        TruckDao truckDao=new TruckDaoImpl();
//        Application application=new Application(truckDao);
//        application.viewTruckRegistry();
//    }
}
