package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        assertDoesNotThrow(() -> lot.park(car, Strategy.NEAREST));
    }

    @Test
    void testThrowingAnExceptionParkingACarWhenSpotsAreNotAvailable() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("123", "Red"), Strategy.NEAREST);
        lot.park(new Car("1234", "Red"), Strategy.NEAREST);
        lot.park(new Car("1235", "Red"), Strategy.NEAREST);
        lot.park(new Car("1236", "Red"), Strategy.NEAREST);
        assertThrows(RuntimeException.class, () -> lot.park(new Car("12324", "Blue"), Strategy.NEAREST));
    }

    @Test
    void testUnParkingACar() {
        ParkingLot lot = new ParkingLot(4);
        Car car1 = new Car("123", "Red");
        Car car2 = new Car("1236", "Blue");
        String token1 = lot.park(car1, Strategy.NEAREST);
        String token2 = lot.park(car2, Strategy.NEAREST);
        assertDoesNotThrow(() -> lot.unPark(token1));
    }
    @Test
    void testUnParkingACarVacatesASpot() throws CarNotFoundException {
        ParkingLot lot = new ParkingLot(4);
        String token1 = lot.park(new Car("123", "Red"), Strategy.NEAREST);
        String token2 = lot.park(new Car("1234", "Red"), Strategy.NEAREST);
        lot.unPark(token1);
        assertDoesNotThrow(() -> {
            lot.park(new Car("123","red"), Strategy.NEAREST);
        });
    }
    @Test
    void testExpectCorrectCarWhenUnParking() throws CarNotFoundException {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("MH123", "Red");
        String token = lot.park(car, Strategy.NEAREST);
        assertEquals(car, lot.unPark(token));
    }
    @Test
    void testThrowingAnExceptionWhenCarNotFound() {
        ParkingLot lot = new ParkingLot(4);
        Car car = new Car("MH123", "Red");
        String token = lot.park(car, Strategy.NEAREST);
        assertThrows(CarNotFoundException.class, () -> lot.unPark("1223"));
    }
    @Test
    void testCountOfCarsByColor1() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("MH123", "Red"), Strategy.NEAREST);
        lot.park(new Car("MH1234", "Blue"), Strategy.NEAREST);
        lot.park(new Car("MH1235", "Red"), Strategy.NEAREST);
        lot.park(new Car("MH1236", "Red"), Strategy.NEAREST);
        assertEquals(3, lot.countCarsByColor("Red"));
    }
    @Test
    void testCountOfCarsByColor2() {
        ParkingLot lot = new ParkingLot(4);
        lot.park(new Car("MH123", "Red"), Strategy.NEAREST);
        lot.park(new Car("MH1234", "Red"), Strategy.NEAREST);
        lot.park(new Car("MH1235", "Red"), Strategy.NEAREST);
        lot.park(new Car("MH1236", "Red"), Strategy.NEAREST);
        assertEquals(0, lot.countCarsByColor("Blue"));
    }

    @Test
    void testObserverIsNotifiedWhenParkingIsFull() {
        Subscriber observer = mock(ParkingAttendant.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("MH123", "Red");
        NotificationBus.instance().subscribe(observer, Event.FULL);
        parkingLot.park(car, Strategy.NEAREST);
        verify(observer).notify(Event.FULL, parkingLot);
    }
    @Test
    void testObserverIsNotifiedWhenParkingBecomesAvailable() throws CarNotFoundException {
        Subscriber observer = mock(ParkingAttendant.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("MH123", "Red");
        NotificationBus.instance().subscribe(observer, Event.EMPTY);
        String token = parkingLot.park(car, Strategy.NEAREST);
        parkingLot.unPark(token);
        verify(observer).notify(Event.EMPTY, parkingLot);
    }


}