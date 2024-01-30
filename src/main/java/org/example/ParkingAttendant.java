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
        for (int i = 0 ; i < this.noOfParkingLots ; i++) {
            ParkingLot lot = parkingLots.get(i);
            if (!lot.isFull()) {
                String token = lot.park(car);
                if (lot.isFull()) {
                    System.out.println("Lot " + (i + 1) + " is Full");
                }
                return token;
            }
        }
        throw new RuntimeException("All the parking lots are full");
    }
    Car unPark(String token) throws CarNotFoundException {
        for (int i = 0 ; i < this.noOfParkingLots ; i++) {
            ParkingLot lot = parkingLots.get(i);
            boolean full = lot.isFull();
            Car car = lot.unPark(token);
            if (car != null) {
                if (full != lot.isFull()) {
                    System.out.println("Lot " + (i + 1) + " is available now");
                }
                return car;
            }
        }
        throw new CarNotFoundException("Car was not found");
    }

}
