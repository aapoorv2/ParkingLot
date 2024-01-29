package org.example;

public class ParkingSpot {
    private boolean occupied = false;
    void park(Car car) {
        this.occupied = true;
    }
    void unPark() {
        this.occupied = false;
    }
    boolean isOccupied() {
        return this.occupied;
    }
}
