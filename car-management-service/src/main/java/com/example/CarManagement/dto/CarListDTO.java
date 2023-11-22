package com.example.CarManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CarListDTO {
    private String make;
    private String model;
    private Date manufacturingDate;
    private int seatsNumber;
    private String registrationNumber;
    private boolean currentCar;


}
