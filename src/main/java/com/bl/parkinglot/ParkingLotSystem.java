/*********************************************************************
 * @Purpose: developing parking lot system
 * @author: Tushar W
 * @date: 25-05-20
 *********************************************************************/
package com.bl.parkinglot;

import com.bl.exception.ParkingLotSystemException;
import com.bl.model.ParkingLotObserver;
import com.bl.model.ParkingLotOwner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParkingLotSystem {
    /**
     * field actualCapacity use to store capacity of parking lot
     * object parkDateAndTime use to store date and time in string format when park vehicle
     * object vehicles use to store vehicles
     * object observers use to store registered observer
     */
    private int actualCapacity;
    private  int totalSlots;
    int i=0, j=0, count=0;
    private String parkDateAndTime;
    Object obj[] = null;
    private Map<Integer, Object> vehicles;
    private List<ParkingLotObserver> observers;
    /**
     * o-argument constructor
     */
    public ParkingLotSystem() {
    }
    /**
     * constructor to take one input as capacity
     * @param capacity
     */
    public ParkingLotSystem(int capacity, int totalSlots) {
        this.actualCapacity = capacity;
        this.totalSlots = totalSlots;
        this.observers = new ArrayList<>();
        initializeSlotsInParkingLot();
    }
    /**
     * method to initialize all slots in parking lot
     */
    public void initializeSlotsInParkingLot(){
        this.vehicles = new HashMap<>();
        obj = new Object[this.actualCapacity];
        for (int slot=0; slot<actualCapacity; slot++){
            vehicles.put(slot, null);
            obj[slot] = new LinkedList();
        }
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
     * method to park given vehicle and slot in parking lot
     * @param vehicle, slot
     * is given vehicle already parked or parking lot is full then
     * @throws ParkingLotSystemException
     */
    public void park(Object vehicle, Boolean driverStatus) throws ParkingLotSystemException {
        int slot = 0;
        if (isVehicleParked(vehicle))
            throw new ParkingLotSystemException("VEHICLE_IS_ALREADY_PARKED",
                                                 ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED);
        if (this.vehicles.size() == this.actualCapacity  && !vehicles.containsValue(null)) {
            for (ParkingLotObserver observer: observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotSystemException("PARKING_LOT_IS_FULL",
                                                ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL);
        }
        if (driverStatus != null)
            slot = this.getSlotNoForHandicapDriver();
        if (driverStatus == null)
           slot = this.getParkingSlots();

        this.vehicles.put(slot, (vehicle +" "+ this.getTimeAndDate()));
        LinkedList list = (LinkedList) obj[slot];
        list.add(this.vehicles.get(slot));
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
        if (isVehicleParked(vehicle)) {
            this.vehicles.remove(vehicle);
            LinkedList list = (LinkedList) obj[getVehicleKey(vehicle)];
            if (list.contains(vehicle))
                list.remove(vehicle);
            new ParkingLotOwner().setDateAndTime(this.getTimeAndDate());
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
        if (this.vehicles.containsValue((vehicle + " " + this.parkDateAndTime)))
            return true;
        return false;
    }
    /**
     * method to check is given vehicle unParked or not
     * @param vehicle
     * @return true or false
     */
    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicles.containsValue(vehicle))
            return false;
        return true;
    }
    /**
     * method to get parking slots for use to park vehicle evenly
     * @return slot no
     */
    private int getParkingSlots() {
        int slotNo;
        if (count == totalSlots) {
            i = i + 1;
            j = 0;
            count = 0;
        }
        slotNo = i + ((actualCapacity / totalSlots) * j);
        LinkedList list = (LinkedList) obj[slotNo];
        if (list.isEmpty())
            return slotNo;
        j = j + 1;
        count = count + 1;
        slotNo = this.getParkingSlots();
        return slotNo;
    }
    /**
     * method to get slot number for park vehicle for handicap driver
     * @return int(slot number)
     */
    private int getSlotNoForHandicapDriver() {
        for (int i = 0; i < actualCapacity; i++) {
            LinkedList list = (LinkedList) obj[i];
            if (list.isEmpty()) {
                int slotNo = i;
                return slotNo;
            }
        }
        return 0;
    }
    /**
     * method to get parked vehicle slot no
     * @param vehicle
     * @return vehicle key in int
     * @throws ParkingLotSystemException
     */
    public int getVehicleKey(Object vehicle) throws ParkingLotSystemException {
        try {
            Integer vehicleKey = vehicles.keySet()
                    .stream()
                    .filter(key -> vehicle.equals(vehicles.get(key)))
                    .findFirst().get();
            return vehicleKey;
        } catch (NoSuchElementException e) {
            throw new ParkingLotSystemException("VEHICLE_NOT_FOUND", ParkingLotSystemException.ExceptionType.VEHICLE_NOT_FOUND);
        }
    }
    /**
     * method to get date in string format
     * @return String
     */
    public String getTimeAndDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        parkDateAndTime = dateFormat.format(date);
        return parkDateAndTime;
    }
}
