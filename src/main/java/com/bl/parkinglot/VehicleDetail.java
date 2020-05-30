package com.bl.parkinglot;

public class VehicleDetail {
    ParkingLotSystem parkingSystem = new ParkingLotSystem();
    public String color;
    String dateAndTime = parkingSystem.getTimeAndDate();

    public VehicleDetail() { }

    public VehicleDetail(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "VehicleDetail{" +
                ", color='" + color + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}
