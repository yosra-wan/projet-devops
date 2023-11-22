package com.example.CarManagement.service;

import com.example.CarManagement.dto.CarDTO;
import com.example.CarManagement.dto.CarListDTO;
import com.example.CarManagement.exception.CarNotFoundException;
import com.example.CarManagement.exception.DriverNotFoundException;
import com.example.CarManagement.model.Car;

import java.util.List;

public interface ICarHistoryServImpl {

    void createCarHistory(CarDTO carDTO) throws CarNotFoundException, DriverNotFoundException;

    List<CarListDTO> getCarsByDriverId(Integer id);
}
