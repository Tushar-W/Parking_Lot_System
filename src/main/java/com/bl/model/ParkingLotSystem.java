/**
 * @Purpose: developing parking lot system
 * @author: Tushar W
 * @date: 25-05-20
 */
package com.bl.model;

import com.bl.exception.ParkingLotSystemException;

public class ParkingLotSystem {
    /**
     * field vehicle use to store vehicle
     */
    private Object vehicle;
    /**
     * method to check vehicle park or not to parking lot
     * @param vehicle
     * @return true or false
     */
    public void park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null) {
            this.vehicle = vehicle;
        }
        throw new ParkingLotSystemException("PARKING_LOT_IS_FULL", ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL);
    }
    /**
     * method to check given vehicle unPark or not from parking lot
     * @param vehicle
     * @return true or false
     */
    public void unPark(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null)
            throw new ParkingLotSystemException("PARKING_LOT_IS_EMPTY", ParkingLotSystemException.ExceptionType.PARKING_LOTS_IS_EMPTY);
        if (this.vehicle.equals(vehicle))
            this.vehicle = null;
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
