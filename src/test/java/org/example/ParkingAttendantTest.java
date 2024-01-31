package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParkingAttendantTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    void testThrowsAnExceptionForNonPositiveNumberOfParkingLots() {
        assertThrows(IllegalArgumentException.class, () -> {
            ParkingAttendant lots = new ParkingAttendant(-5, 10);
        });
    }
    @Test
    void testDoesNotThrowExceptionWhenParkingACarAndSlotsAreAvailable() {
        ParkingAttendant lots = new ParkingAttendant(10, 10);
        assertDoesNotThrow(() -> {
            lots.park(new Car("MH123", "Red"), Strategy.NEAREST);
        });
    }
    @Test
    void testThrowsAnExceptionWhenParkingACarAndSlotsAreUnavailable() {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        lots.park(new Car("MH1234", "Blue"), Strategy.NEAREST);
        lots.park(new Car("M1234", "Red"), Strategy.NEAREST);
        assertThrows(RuntimeException.class, () -> {
            lots.park(new Car("MH123", "Red"), Strategy.NEAREST);
        });
    }
    @Test
    void testDoesNotThrowAnExceptionWhenUnParkingACarAndLotsAreNotEmpty() {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        lots.park(new Car("MH1234", "Blue"), Strategy.NEAREST);
        String token = lots.park(new Car("M1234", "Red"), Strategy.NEAREST);
        assertDoesNotThrow(() -> {
            lots.unPark(token);
        });
    }
    @Test
    void testExpectCorrectCarWhenUnParking() throws CarNotFoundException {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        Car car = new Car("M1234", "Red");
        String token = lots.park(car, Strategy.NEAREST);
        assertEquals(car, lots.unPark(token));
    }
    @Test
    void testTwoAttendantsCanAccessTheSameLotIfAssignedAndOneCanParkWhileTheOtherCanUnPark() throws CarNotFoundException {
        ParkingAttendant attendant1 = new ParkingAttendant(0, 0);
        ParkingAttendant attendant2 = new ParkingAttendant(0, 0);
        ParkingLot lot = new ParkingLot(1);
        attendant1.assign(lot);
        attendant2.assign(lot);
        Car car = new Car("MH12","Blue");
        String token = attendant1.park(car, Strategy.NEAREST);
        assertEquals(car, attendant2.unPark(token));
    }
    @Test
    void testThrowingAnExceptionWhenTwoAttendantsCanAccessTheSameLotIfAssignedAndLotIsFull() throws CarNotFoundException {
        ParkingAttendant attendant1 = new ParkingAttendant(0, 0);
        ParkingAttendant attendant2 = new ParkingAttendant(0, 0);
        ParkingLot lot = new ParkingLot(1);
        attendant1.assign(lot);
        attendant2.assign(lot);
        Car car = new Car("MH12","Blue");
        String token = attendant1.park(car, Strategy.NEAREST);
        assertThrows(RuntimeException.class, () -> {
            attendant2.park(car, Strategy.NEAREST);
        });
    }

    @Test
    void testNotThrowingAnExceptionWhenParkingACarUsingTheFarthestStrategy() {
        ParkingAttendant attendant = new ParkingAttendant(2, 2);
        assertDoesNotThrow(() -> {
            attendant.park(new Car("MH123", "Red"), Strategy.FARTHEST);
        });
    }
    @Test
    void testParksTheCarFarthestInTheLot() {
        ParkingAttendant attendant = new ParkingAttendant(0, 0);
        ParkingLot lot1 = new ParkingLot(2);
        ParkingLot lot2 = new ParkingLot(2);
        attendant.assign(lot1);
        attendant.assign(lot2);
        attendant.park(new Car("MH123", "Red"), Strategy.FARTHEST);
        attendant.park(new Car("MH123", "Red"), Strategy.FARTHEST);
        assertTrue(lot2.isFull());
        assertFalse(lot1.isFull());

    }
}