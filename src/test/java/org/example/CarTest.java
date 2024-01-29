package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    void testGetColor() {
        Car car = new Car("123", "Blue");
        assertEquals("Blue", car.getColor());
    }
    @Test
    void testGetRegistrationNumber() {
        Car car = new Car("123", "Blue");
        assertEquals("123", car.getRegistrationNumber());
    }

}