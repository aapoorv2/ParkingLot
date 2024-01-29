package org.example;

public class ParkingLots {
    private ParkingLot[] parkingLots;
    private final int noOfParkingLots;
    private int availableLots;
    ParkingLots(int noOfParkingLots, int noOfSpotsPerLot) {
        if (noOfParkingLots <= 0) {
            throw new IllegalArgumentException("Number of Parking Lots cannot be negative");
        }
        this.parkingLots = new ParkingLot[noOfParkingLots];
        for (int i = 0 ; i < noOfParkingLots ; i++) {
            parkingLots[i] = new ParkingLot(noOfSpotsPerLot);
        }
        this.noOfParkingLots = noOfParkingLots;
        this.availableLots = noOfParkingLots;
    }
    void park(Car car) {
        if (isFull()) {
            throw new IllegalArgumentException("All the parking lots are full");
        }
        for (ParkingLot lot : parkingLots) {
            if (!lot.isFull()) {
                lot.park(car);
                if (lot.isFull()) {
                    availableLots--;
                }
            }
        }
    }
//    Car unPark(String registrationNumber) {
//
//    }
    boolean isFull() {
        return availableLots == 0;
    }

}
