package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {
    @Test
    void testIsOccupiedIfSpotIsOccupied() {
        ParkingSpot spot = new ParkingSpot();
        Car car = new Car("1234", "Black");
        spot.park(car);
        assertTrue(spot.isOccupied());
    }
    @Test
    void testIsOccupiedIfSpotNotOccupied() {
        ParkingSpot spot = new ParkingSpot();
        assertFalse(spot.isOccupied());
    }

}