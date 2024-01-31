package org.example;

import java.util.List;

public enum Strategy {
    NEAREST {
        @Override
        ParkingLot findParkingLot(List<ParkingLot> lots) {
            for (ParkingLot lot : lots) {
                if (!lot.isFull()) {
                    return lot;
                }
            }
            return null;
        }
        @Override
        ParkingSpot findParkingSpot(List<ParkingSpot> spots) {
            for (ParkingSpot spot : spots) {
                if (!spot.isOccupied()) {
                    return spot;
                }
            }
            return null;
        }
    },
    FARTHEST {
        @Override
        ParkingLot findParkingLot(List<ParkingLot> lots) {
            for (int i = lots.size() - 1 ; i >= 0 ; i--) {
                ParkingLot lot = lots.get(i);
                if (!lot.isFull()) {
                    return lot;
                }
            }
            return null;
        }
        @Override
        ParkingSpot findParkingSpot(List<ParkingSpot> spots) {
            for (int i = spots.size() - 1 ; i >= 0 ; i--) {
                ParkingSpot spot = spots.get(i);
                if (!spot.isOccupied()) {
                    return spot;
                }
            }
            return null;
        }

    };
    abstract ParkingSpot findParkingSpot(List<ParkingSpot> spots);
    abstract ParkingLot findParkingLot(List<ParkingLot> lots);

}
