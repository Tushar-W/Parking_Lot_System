package com.bl.model;

public class ParkingLotOwner {
    private boolean capacityIsFull;

    public void capacityIsFull() {
        this.capacityIsFull = true;
    }

    public boolean isCapacityFull() {
        return this.capacityIsFull;
    }
}
