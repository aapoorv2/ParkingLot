package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotsTest {
    @Test
    void testThrowsAnExceptionForNonPositiveNumberOfParkingLots() {
        assertThrows(IllegalArgumentException.class, () -> {
            ParkingLots lots = new ParkingLots(-5, 10);
        });
    }
    @Test
    void testDoesNotThrowExceptionWhenParkingACarAndSlotsAreAvailable() {
        ParkingLots lots = new ParkingLots(10, 10);
        assertDoesNotThrow(() -> {
            lots.park(new Car("MH123", "Red"));
        });
    }
    @Test
    void testThrowsAnExceptionWhenParkingACarAndSlotsAreUnavailable() {
        ParkingLots lots = new ParkingLots(1, 2);
        lots.park(new Car("MH1234", "Blue"));
        lots.park(new Car("M1234", "Red"));
        assertThrows(IllegalArgumentException.class, () -> {
            lots.park(new Car("MH123", "Red"));
        });
    }

}