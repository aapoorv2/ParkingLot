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
            lots.park(new Car("MH123", "Red"));
        });
    }
    @Test
    void testThrowsAnExceptionWhenParkingACarAndSlotsAreUnavailable() {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        lots.park(new Car("MH1234", "Blue"));
        lots.park(new Car("M1234", "Red"));
        assertThrows(RuntimeException.class, () -> {
            lots.park(new Car("MH123", "Red"));
        });
    }
    @Test
    void testDoesNotThrowAnExceptionWhenUnParkingACarAndLotsAreNotEmpty() {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        lots.park(new Car("MH1234", "Blue"));
        String token = lots.park(new Car("M1234", "Red"));
        assertDoesNotThrow(() -> {
            lots.unPark(token);
        });
    }
    @Test
    void testExpectCorrectCarWhenUnParking() throws CarNotFoundException {
        ParkingAttendant lots = new ParkingAttendant(1, 2);
        Car car = new Car("M1234", "Red");
        String token = lots.park(car);
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
        String token = attendant1.park(car);
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
        String token = attendant1.park(car);
        assertThrows(RuntimeException.class, () -> {
            attendant2.park(car);
        });
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void testPrintsTheUnavailabilityOfALotWhenItBecomesFull() {
        ParkingAttendant attendant = new ParkingAttendant(1, 1);
        attendant.park(new Car("MH123", "Red"));
        assertEquals("Lot 1 is Full", outputStreamCaptor.toString().trim());
    }
    @Test
    void testPrintsTheAvailabilityOfALotWhenItBecomesAvailable() {
        ParkingAttendant attendant = new ParkingAttendant(1, 1);
        attendant.park(new Car("MH123", "Red"));
        assertEquals("Lot 1 is Full", outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}