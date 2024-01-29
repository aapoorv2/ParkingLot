package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void testThrowsAnExceptionForNonPositiveNumberOfSpots() {
        assertThrows(IllegalArgumentException.class, () -> {
            ParkingLot lot = new ParkingLot(-2);
        });
    }
    @Test
    void testCountAvailableSpots() {
        ParkingLot lot = new ParkingLot(4);
        assertEquals(4, lot.countAvailableSpots());
    }

    @Test
    void testParkingACarWhenSpotIsAvailable() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("123", "Red");
        assertDoesNotThrow(() -> lot.park(car));
    }
    @Test
    void testThrowingAnExceptionParkingACarWhenSpotsAreNotAvailable() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("123", "Red"));
        lot.park(new Car("123", "Red"));
        lot.park(new Car("123", "Red"));
        lot.park(new Car("123", "Red"));
        assertThrows(IllegalArgumentException.class, () -> lot.park(new Car("1234", "Blue")));
    }
    @Test
    void testUnParkingACar() {
        ParkingLot lot = new ParkingLot(4);
        Car car1 = new Car("123", "Red");
        Car car2 = new Car("1234", "Blue");
        lot.park(car1);
        lot.park(car2);
        assertDoesNotThrow(() -> lot.unPark("123"));
    }
    @Test
    void testThrowingAnExceptionUnParkingACarWhenLotIsEmpty() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("123", "Red");
        assertThrows(IllegalArgumentException.class, () -> lot.unPark("123"));
    }
    @Test
    void testExpectCorrectCarWhenUnParking() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("MH123", "Red");
        lot.park(car);
        assertEquals(car, lot.unPark("MH123"));
    }
    @Test
    void testThrowingAnExceptionWhenCarNotFound() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("MH123", "Red");
        lot.park(car);
        assertThrows(IllegalArgumentException.class, () -> lot.unPark("DL431"));
    }
    @Test
    void testCountOfCarsByColor1() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("MH123", "Red"));
        lot.park(new Car("MH1234", "Blue"));
        lot.park(new Car("MH1235", "Red"));
        lot.park(new Car("MH1236", "Red"));
        assertEquals(3, lot.countCarsByColor("Red"));
    }
    @Test
    void testCountOfCarsByColor2() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("MH123", "Red"));
        lot.park(new Car("MH1234", "Red"));
        lot.park(new Car("MH1235", "Red"));
        lot.park(new Car("MH1236", "Red"));
        assertEquals(0, lot.countCarsByColor("Blue"));
    }
}