package com.bl.exception;

public class ParkingLotSystemException extends Throwable {
    /**
     * enum for take constants Exception Types
     */
    public enum ExceptionType{
        PARKING_LOTS_IS_EMPTY, PARKING_LOT_FULL, VEHICLE_ALREADY_PARKED, VEHICLE_NOT_FOUND;
    }
    /**
     * object of enum class ExceptionType
     */
    public ExceptionType type;
    /**
     * constructor to take two input values
     * @param type
     * @param message
     */
    public ParkingLotSystemException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
