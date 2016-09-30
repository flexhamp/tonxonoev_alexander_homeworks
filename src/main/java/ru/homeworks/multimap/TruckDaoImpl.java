package ru.homeworks.multimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexander on 22.09.2016.
 */
public class TruckDaoImpl implements TruckDao {
    private final List<Truck> trucks=new ArrayList<>(
            Arrays.asList(
                    new Truck(1, 200, "Scania"),
                    new Truck(2, 150, "Volvo"),
                    new Truck(3, 175, "MAN"),
                    new Truck(4, 190, "Mercedes-Benz"),
                    new Truck(5, 125, "KAMAZ"),
                    new Truck(6, 195, "IVECO"),
                    new Truck(7, 120, "IVECO")
            )
    );
    @Override
    public List<Truck> toList() {
        return trucks;
    }
}
