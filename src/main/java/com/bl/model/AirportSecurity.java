package com.bl.model;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isCapacityIsFull;

    @Override
    public void capacityIsFull() {
        this.isCapacityIsFull = true;
    }

    @Override
    public void capacityIsAvailable() {
        this.isCapacityIsFull = false;
    }
    /**
     * method to get parking lot capacity is full or not
     * @return true or false
     */
    public boolean isCapacityFull() {
        return this.isCapacityIsFull;
    }
}
