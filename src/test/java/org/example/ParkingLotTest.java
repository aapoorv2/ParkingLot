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
    void testParkingACarWhenSpotIsAvailable() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("123", "Red");
        assertDoesNotThrow(() -> lot.park(car));
    }
    @Test
    void testThrowingAnExceptionWhenParkingCarsWithSameRegNumber() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("123", "Red"));
        assertThrows(RuntimeException.class, () -> lot.park(new Car("123", "Red")));
    }
    @Test
    void testThrowingAnExceptionParkingACarWhenSpotsAreNotAvailable() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("123", "Red"));
        lot.park(new Car("1234", "Red"));
        lot.park(new Car("1235", "Red"));
        lot.park(new Car("1236", "Red"));
        assertThrows(IllegalArgumentException.class, () -> lot.park(new Car("12324", "Blue")));
    }

    @Test
    void testUnParkingACar() {
        ParkingLot lot = new ParkingLot(4);
        Car car1 = new Car("123", "Red");
        Car car2 = new Car("1236", "Blue");
        lot.park(car1);
        lot.park(car2);
        assertDoesNotThrow(() -> lot.unPark("123"));
    }
    @Test
    void testUnParkingACarVacatesASpot() throws CarNotFoundException {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("123", "Red"));
        lot.park(new Car("1234", "Red"));
        lot.park(new Car("1235", "Red"));
        lot.park(new Car("1236", "Red"));
        lot.unPark("123");
        assertDoesNotThrow(() -> {
            lot.park(new Car("123","red"));
        });
    }
    @Test
    void testThrowingAnExceptionUnParkingACarWhenLotIsEmpty() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("123", "Red");
        assertThrows(IllegalArgumentException.class, () -> lot.unPark("123"));
    }
    @Test
    void testExpectCorrectCarWhenUnParking() throws CarNotFoundException {
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
        assertThrows(CarNotFoundException.class, () -> lot.unPark("DL431"));
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