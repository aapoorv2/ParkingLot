package org.example;

import java.util.*;

public class ParkingLot {
    private ParkingSpot[] spots;
    private final int noOfSpots;
    private int availableSpots;
    Map<String, Integer> colorCount = new HashMap<>();
    ParkingLot(int noOfSpots) {
        if (noOfSpots <= 0) {
            throw new IllegalArgumentException("Number of Slots can't be non positive");
        }
        this.spots = new ParkingSpot[noOfSpots];
        for (int i = 0 ; i < noOfSpots ; i++) {
            this.spots[i] = new ParkingSpot();
        }
        this.noOfSpots = noOfSpots;
        this.availableSpots = noOfSpots;
    }
    ParkingSpot findParkingSpot(Strategy strategy) {
        if (strategy == Strategy.NEAREST) {
            for (int i = 0 ; i < noOfSpots ; i++) {
                ParkingSpot spot = spots[i];
                if (!spot.isOccupied()) {
                    return spot;
                }
            }
        } else if (strategy == Strategy.FARTHEST) {
            for (int i = noOfSpots - 1 ; i >= 0 ; i--) {
                ParkingSpot spot = spots[i];
                if (!spot.isOccupied()) {
                    return spot;
                }
            }
        }

        return null;
    }
    String park(Car car) {
        if (isFull()) {
            throw new RuntimeException("Parking is Full");
        }
        ParkingSpot spot = findParkingSpot(Strategy.NEAREST);
        colorCount.put(car.color(), colorCount.getOrDefault(car.color(), 0) + 1);
        availableSpots--;
        return spot.park(car);
    }
    Car unPark(String token) throws CarNotFoundException {
        if (isEmpty()) {
            throw new RuntimeException("Parking is Empty");
        }
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.isValidToken(token)) {
                Car car = spot.unPark(token);
                colorCount.put(car.color(), colorCount.getOrDefault(car.color(), 0) - 1);
                availableSpots++;
                return car;
            }
        }
        throw new CarNotFoundException("Car Not Found");
    }
    boolean isFull() {
        return availableSpots == 0;
    }
    boolean isEmpty() {
        return availableSpots == noOfSpots;
    }
    int countCarsByColor(String color) {
        return colorCount.getOrDefault(color, 0);
    }

}
