package com.example.CarManagement.service;

import com.example.CarManagement.exception.AlreadyExistsException;
import com.example.CarManagement.model.Car;

import java.util.List;

public interface ICarService {


    void createCar(Car car) throws AlreadyExistsException;

    List<Car> getAllCars();
}
