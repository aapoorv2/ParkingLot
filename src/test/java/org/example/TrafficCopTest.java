package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TrafficCopTest {

    @Test
    void testTrafficCopIsNotifiedForAllTheLotsIfFull() throws CarNotFoundException {
        Subscriber cop = mock(TrafficCop.class);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        ParkingLot lot3 = new ParkingLot(1);
        NotificationBus.instance().subscribe(cop, Event.FULL);
        lot1.park(new Car("MH123", "Red"), Strategy.NEAREST);
        lot2.park(new Car("MH1243", "Red"), Strategy.NEAREST);
        lot3.park(new Car("MH9123", "Red"), Strategy.NEAREST);
        verify(cop).notify(Event.FULL, lot1);
        verify(cop).notify(Event.FULL, lot2);
        verify(cop).notify(Event.FULL, lot3);
    }
    @Test
    void testTrafficCopIsNotifiedForAllTheLotsIfTheyBecomeAvailable() throws CarNotFoundException {
        Subscriber cop = mock(TrafficCop.class);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        ParkingLot lot3 = new ParkingLot(1);
        NotificationBus.instance().subscribe(cop, Event.EMPTY);
        lot1.unPark(lot1.park(new Car("MH123", "Red"), Strategy.NEAREST));
        lot2.unPark(lot2.park(new Car("MH1243", "Red"), Strategy.NEAREST));
        lot3.unPark(lot3.park(new Car("MH9123", "Red"), Strategy.NEAREST));
        verify(cop).notify(Event.EMPTY, lot1);
        verify(cop).notify(Event.EMPTY, lot2);
        verify(cop).notify(Event.EMPTY, lot3);
    }

}