package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private ParkingSpot[] spots;
    private final int noOfSpots;
    private Map<ParkingSpot, Car> spotToCar;
    private int availableSpots;
    ParkingLot(int noOfSpots) {
        if (noOfSpots <= 0) {
            throw new IllegalArgumentException("Number of Slots can't be non positive");
        }
        this.spots = new ParkingSpot[noOfSpots];
        for (int i = 0 ; i < noOfSpots ; i++) {
            this.spots[i] = new ParkingSpot();
        }
        this.spotToCar = new HashMap<ParkingSpot, Car>();
        this.noOfSpots = noOfSpots;
        this.availableSpots = noOfSpots;
    }
    void park(Car car) {
        if (isFull()) {
            throw new IllegalArgumentException("Parking is Full");
        }
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                spot.park(car);
                spotToCar.put(spot, car);
                availableSpots--;
                break;
            }
        }
    }
    Car unPark(String registrationNumber) throws CarNotFoundException {
        if (isEmpty()) {
            throw new IllegalArgumentException("Parking is Empty");
        }
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && Objects.equals(spotToCar.get(spot).regNo(), registrationNumber)) {
                availableSpots++;
                spot.unPark();
                return spotToCar.remove(spot);
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
    int countAvailableSpots() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                count++;
            }
        }
        return count;
    }
    boolean isFull() {
        return availableSpots == 0;
    }
    boolean isEmpty() {
        return availableSpots == noOfSpots;
    }
    int countCarsByColor(String color) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (Objects.equals(spotToCar.get(spot).color(), color)) {
                count++;
            }
        }
        return count;
    }

}
