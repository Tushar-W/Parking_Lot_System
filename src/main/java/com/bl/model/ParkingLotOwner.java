package com.bl.model;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean capacityIsFull;

    public void capacityIsFull() {
        this.capacityIsFull = true;
    }

    public boolean isCapacityFull() {
        return this.capacityIsFull;
    }
}
