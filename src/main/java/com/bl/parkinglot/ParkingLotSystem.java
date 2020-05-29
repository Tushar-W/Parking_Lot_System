/*********************************************************************
 * @Purpose: developing parking lot system
 * @author: Tushar W
 * @date: 25-05-20
 *********************************************************************/
package com.bl.parkinglot;

import com.bl.exception.ParkingLotSystemException;
import com.bl.model.ParkingLotObserver;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    /**
     * field actualCapacity use to store capacity of parking lot
     * object vehicles use to store vehicles
     * object observers use to store registered observer
     */
    private int actualCapacity;
    private List vehicles;
    private List<ParkingLotObserver> observers;
    /**
     * constructor to take one input as capacity
     * @param capacity
     */
    public ParkingLotSystem(int capacity) {
        this.actualCapacity = capacity;
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
    }
    /**
     * method to registered parking lot observer and add into observers list
     * @param observer
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }
    /**
     * method to set capacity of parking lot
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }
    /**
     * method to park given vehicle in parking lot
     * @param vehicle
     * is given vehicle already parked or parking lot is full then
     * @throws ParkingLotSystemException
     */
    public void park(Object vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotSystemException("VEHICLE_IS_ALREADY_PARKED",
                    ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED);
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer: observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotSystemException("PARKING_LOT_IS_FULL",
                                                ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL);
        }
        this.vehicles.add(vehicle);
    }
    /**
     * method unPark given vehicle from parking lot
     * @param vehicle
     * is given vehicle not parked and try to unPark or parking lot is empty then
     * @throws ParkingLotSystemException
     */
    public void unPark(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicles.isEmpty())
            throw new ParkingLotSystemException("PARKING_LOT_IS_EMPTY",
                    ParkingLotSystemException.ExceptionType.PARKING_LOTS_IS_EMPTY);
        if (isVehicleUnParked(vehicle))
            throw new ParkingLotSystemException("VEHICLE_IS_NOT_PARKED",
                                                ParkingLotSystemException.ExceptionType.VEHICLE_NOT_PARKED);
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
        }
    }
    /**
     * method to check is given vehicle parked or not
     * @param vehicle
     * @return true or false
     */
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }
    /**
     * method to check is given vehicle unParked or not
     * @param vehicle
     * @return true or false
     */
    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return false;
        return true;
    }
}
