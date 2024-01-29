package org.example;

public class Car {
    private final String registrationNumber;
    private final String color;
    Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
    String getColor(){
        return this.color;
    }
    String getRegistrationNumber() {
        return this.registrationNumber;
    }
}
