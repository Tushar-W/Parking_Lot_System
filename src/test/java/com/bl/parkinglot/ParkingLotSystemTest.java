package com.bl.parkinglot;

import com.bl.exception.ParkingLotSystemException;
import com.bl.model.AirportSecurity;
import com.bl.model.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object[] vehicle = null;

    @Before
    public void setUp() throws Exception {
        int capacity = 10;
        parkingLotSystem = new ParkingLotSystem(capacity, 2);
        vehicle = new Object[capacity];
        for (int i = 0; i < capacity; i++)
            vehicle[i] = new Object();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle[0]);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle[0]);
            Assert.assertTrue(isParked);
        } catch (ParkingLotSystemException e) { }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnThrowException() {
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[0]);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED,e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenParkedAfterParkingLotFull_ShouldReturnThrowException() {
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            parkingLotSystem.park(vehicle[2]);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_FULL,e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.unPark(vehicle[0]);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotSystemException e) { }
    }

    @Test
    public void givenAVehicle_WhenUnParkAfterParkingLotIsEmpty_ShouldReturnThrowException() {
        try {
            parkingLotSystem.unPark(vehicle[0]);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOTS_IS_EMPTY,e.type);
        }
    }

    @Test
    public void givenParkingLotStatus_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            parkingLotSystem.park(vehicle[2]);
            parkingLotSystem.park(vehicle[3]);
            parkingLotSystem.park(vehicle[4]);
            parkingLotSystem.park(vehicle[5]);
            parkingLotSystem.park(vehicle[6]);
            parkingLotSystem.park(vehicle[7]);
            parkingLotSystem.park(vehicle[8]);
            parkingLotSystem.park(vehicle[9]);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotSystemException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCapacity_WhenCapacityIs2_ShouldBeAbleToPark2Vehicle() {
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle[0]);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotSystemException e) {
        }
    }

    @Test
    public void givenParkingLotStatus_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            parkingLotSystem.park(vehicle[2]);
            parkingLotSystem.park(vehicle[3]);
            parkingLotSystem.park(vehicle[4]);
            parkingLotSystem.park(vehicle[5]);
            parkingLotSystem.park(vehicle[6]);
            parkingLotSystem.park(vehicle[7]);
            parkingLotSystem.park(vehicle[8]);
            parkingLotSystem.park(vehicle[9]);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotSystemException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenParkingLotStatus_WhenParkingLotHasSpaceAgain_ShouldReturnTrue() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObserver(owner);
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            parkingLotSystem.park(vehicle[2]);
            parkingLotSystem.unPark(vehicle[2]);
        } catch (ParkingLotSystemException e) { }
        boolean capacityFull = owner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicleWithPosition_WhenParkedByPosition_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle[0]);
            boolean vehicleParked = parkingLotSystem.isVehicleParked(vehicle[0]);
            Assert.assertTrue(vehicleParked);
        } catch (ParkingLotSystemException e) { }

    }

    @Test
    public void givenVehicle_WhenVehicleIsFound_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle[0]);
            parkingLotSystem.park(vehicle[1]);
            boolean parked = parkingLotSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(parked);
        } catch (ParkingLotSystemException e) { }
    }

    @Test
    public void givenAVehicleAndParkedTime_WhenVehicleIsParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle[0]);
            String timeAndDate = new ParkingLotSystem().getTimeAndDate();
            parkingLotSystem.unPark(vehicle[0]);
            Assert.assertEquals(timeAndDate, new ParkingLotOwner().getDateAndTime());
        } catch (ParkingLotSystemException e) {
        }
    }

    @Test
    public void givenVehicleList_whenVehicleParkedEvenly_ShouldReturnTrue() {
        try {
             parkingLotSystem.park(this.vehicle[0]);
             parkingLotSystem.park(this.vehicle[1]);
            int vehicleKey = parkingLotSystem.getVehicleKey(vehicle[0]);
            int vehicleKey1 = parkingLotSystem.getVehicleKey(vehicle[1]);
            Assert.assertEquals(0, vehicleKey);
            Assert.assertEquals(5, vehicleKey1);
        } catch (ParkingLotSystemException e) {
        }
    }

}
