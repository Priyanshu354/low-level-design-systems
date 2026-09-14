package lld.parkinglot.entity;

import lld.parkinglot.enums.ParkingStatus;

import java.time.Instant;

public class Ticket {

    private Long id;
    private Vehicle vehicle;
    private ParkingSpace space;
    private Instant entryTime;
    private Instant exitTime;
    private Integer price;
    private ParkingStatus status;
}
