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
    @Test
    void testCorrectCarIsReturnedIfUnParkingACar() {
        ParkingSpot spot = new ParkingSpot();
        Car car = new Car("MH12", "Blue");
        String token = spot.park(car);
        assertEquals(car, spot.unPark(token));
    }
    @Test
    void testThrowsAnExceptionIfParkingOnAnOccupiedSpot() {
        ParkingSpot spot = new ParkingSpot();
        Car car = new Car("1234", "Black");
        spot.park(car);

        assertThrows(RuntimeException.class, () -> {
            spot.park(car);
        });
    }


}