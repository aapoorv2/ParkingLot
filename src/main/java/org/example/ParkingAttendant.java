package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant {
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
    String park(Car car) {
        for (ParkingLot lot : parkingLots) {
            if (!lot.isFull()) {
                return lot.park(car);
            }
        }
        throw new RuntimeException("All the parking lots are full");
    }
    Car unPark(String token) throws CarNotFoundException {
        for (ParkingLot lot : parkingLots) {
            Car car = lot.unPark(token);
            if (car != null) {
                return car;
            }
        }
        throw new CarNotFoundException("Car was not found");
    }

}
