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
    String park(Car car) {
        if (isFull()) {
            throw new RuntimeException("Parking is Full");
        }
        String token = "";
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                token = spot.park(car);
                colorCount.put(car.color(), colorCount.getOrDefault(car.color(), 0) + 1);
                availableSpots--;
                break;
            }
        }
        return token;
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
