package org.example;

import java.util.Objects;
import java.util.UUID;

public class ParkingSpot {
    private boolean occupied = false;
    private Car car;
    private String token;
    String park(Car car) {
        if (this.occupied) {
            throw new RuntimeException("Spot is already Occupied");
        }
        this.car = car;
        this.token = UUID.randomUUID().toString();
        this.occupied = true;
        return this.token;
    }
    Car unPark(String token) {
        if (!this.occupied) {
            throw new RuntimeException("Spot is Empty");
        }
        if (!isValidToken(token)) {
            throw new RuntimeException("Invalid Token.");
        }
        Car car = this.car;
        this.car = null;
        this.token = null;
        this.occupied = false;
        return car;
    }
    boolean isOccupied() {
        return this.occupied;
    }
    boolean isValidToken(String token) {
        return Objects.equals(this.token, token);
    }
}
