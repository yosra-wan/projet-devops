package com.example.CarManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CarDTO {

    private Integer id;
//    private String make;
//    private String model;
//    private Date manufacturingDate;
//    private int seatsNumber;
//    private String registrationNumber;
    private Integer currentUserId;
    private List<DriverDTO> driverDTOS;

}
