package org.example;

public class ParkingSpot {
    private boolean occupied = false;
    void park(Car car) {
        if (this.occupied) {
            throw new RuntimeException("Spot is already Occupied");
        }
        this.occupied = true;
    }
    void unPark() {
        this.occupied = false;
    }
    boolean isOccupied() {
        return this.occupied;
    }
}
