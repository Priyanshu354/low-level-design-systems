package lld.parkinglot.entity;

import lld.parkinglot.enums.VehicleType;

import java.util.Vector;

public class Vehicle {

    private Long id;
    private String vehicleNo;
    private VehicleType vehicleType;

    public Long getId(){
        return id;
    }

    public String getVehicleNo(){
        return vehicleNo;
    }

    public VehicleType getVehicleType(){
        return vehicleType;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setVehicleType(VehicleType vehicleType){
        this.vehicleType = vehicleType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Vehicle (Long id, String vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.id = id;
    }


}
