package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant implements Subscriber{
    private List<ParkingLot> parkingLots;
    private int noOfParkingLots;

    ParkingAttendant(int noOfParkingLots, int noOfSpotsPerLot) {
        if (noOfParkingLots < 0) {
            throw new IllegalArgumentException("Number of Parking Lots cannot be negative");
        }
        this.parkingLots = new ArrayList<>();
        for (int i = 0 ; i < noOfParkingLots ; i++) {
            parkingLots.add(new ParkingLot(noOfSpotsPerLot));
        }
        this.noOfParkingLots = noOfParkingLots;

    }
    void assign(ParkingLot lot) {
        this.parkingLots.add(lot);
        this.noOfParkingLots++;

    }

    String park(Car car, Strategy strategy) {
        ParkingLot lot = strategy.findParkingLot(parkingLots);
        if (lot == null) {
            throw new RuntimeException("All the parking lots are full");
        }
        return lot.park(car, strategy);

    }
    Car unPark(String token) throws CarNotFoundException {
        for (int i = 0 ; i < this.noOfParkingLots ; i++) {
            ParkingLot lot = parkingLots.get(i);
            Car car = lot.unPark(token);
            if (car != null) {
                return car;
            }
        }
        throw new CarNotFoundException("Car was not found");
    }

}
