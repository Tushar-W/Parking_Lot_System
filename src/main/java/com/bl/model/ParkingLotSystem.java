/**
 * @Purpose: developing parking lot system
 * @author: Tushar W
 * @date: 25-05-20
 */
package com.bl.model;

import com.bl.exception.ParkingLotSystemException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    /**
     * field vehicle use to store vehicle
     * field actualCapacity use to store capacity of parking lot
     * field currentCapacity use to store current capacity of parking lot
     */
    private Object vehicle;
    private List vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    /**
     * constructor to take one input as capacity
     * @param capacity
     */
    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }
    /**
     * method to check vehicle park or not to parking lot
     * @param vehicle
     * @return true or false
     */
    public void park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer: observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotSystemException("PARKING_LOT_IS_FULL",
                                                ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL);
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotSystemException("VEHICLE_IS_ALREADY_PARKED",
                                                 ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED);
        this.vehicles.add(vehicle);

    }
    /**
     * method to check given vehicle unPark or not from parking lot
     * @param vehicle
     * @return true or false
     */
    public void unPark(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null)
            throw new ParkingLotSystemException("PARKING_LOT_IS_EMPTY", ParkingLotSystemException.ExceptionType.PARKING_LOTS_IS_EMPTY);
        if (this.vehicles.contains(vehicle))
            this.vehicles.remove(vehicle);
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }
}
