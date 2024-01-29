package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testThrowsAnExceptionForInvalidRegistrationNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
           Car car = new Car("$123", "Blue");
        });
    }
    @Test
    void testDoesNotThrowExceptionForAValidCar() {
        assertDoesNotThrow(() -> {
            Car car = new Car("123CAR", "Red");
        });
    }
}