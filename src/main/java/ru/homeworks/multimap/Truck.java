package ru.homeworks.multimap;

/**
 * Created by Alexander on 22.09.2016.
 */
public class Truck {
    private long id;
    private int capacity;
    private String type;

    public Truck(long id, int capacity, String type) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Truck[ " +
                "id="+id+
                ", capacity="+capacity+
                ", type="+type+'\''+
                ']';
    }

    public long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }
}
