package org.example;

public class Car {
    private final String registrationNumber;
    private final String color;
    Car(String registrationNumber, String color) {
        if (!registrationNumber.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Registration number must be alphanumeric");
        }
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
    String regNo() {
        return new String(this.registrationNumber);
    }
    String color() {
        return new String(this.color);
    }

}
