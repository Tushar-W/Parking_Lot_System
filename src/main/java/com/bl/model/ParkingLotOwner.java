package com.bl.model;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isCapacityIsFull;
    private static String parkedDateAndTime;

    @Override
    public void capacityIsFull() {
        this.isCapacityIsFull = true;
    }

    @Override
    public void capacityIsAvailable() {
        this.isCapacityIsFull = false;
    }
    /**
     * method to check parking lot capacity full or not
     * @return
     */
    public boolean isCapacityFull() {
        return this.isCapacityIsFull;
    }
    /**
     * method to set time and date when park vehicle
     * @param timeAndDate
     */
    public void setDateAndTime(String timeAndDate) {
        this.parkedDateAndTime = timeAndDate;
    }
    /**
     * method to get date and time of parked vehicle
     * @return date and time in String format
     */
    public String getDateAndTime(){
        return parkedDateAndTime;
    }
}
