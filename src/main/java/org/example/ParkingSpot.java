package org.example;

public class ParkingSpot {
    private boolean occupied = false;
    void occupy() {
        this.occupied = true;
    }
    boolean isOccupied() {
        return this.occupied;
    }
}
