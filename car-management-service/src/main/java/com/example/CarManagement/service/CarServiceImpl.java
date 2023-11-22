package com.example.CarManagement.service;

import com.example.CarManagement.dto.CarDTO;
import com.example.CarManagement.exception.AlreadyExistsException;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements ICarService {


    @Autowired
    private CarRepo carRepo;

    @Override
    public void createCar(Car car) throws AlreadyExistsException {

        Car existingCar = carRepo.findByRegistrationNumber(car.getRegistrationNumber().toLowerCase());

        if (existingCar != null) {
            throw new AlreadyExistsException("Car with the given registration number already exists.");
        }

        car.setMake(car.getMake().toLowerCase());
        car.setModel(car.getModel().toLowerCase());
        car.setManufacturingDate(car.getManufacturingDate());
        car.setSeatsNumber(car.getSeatsNumber());
        car.setRegistrationNumber(car.getRegistrationNumber().toLowerCase());
        // car.setLat(car.getLat());
        // car.setLongi(car.getLongi());

        carRepo.save(car);

    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = carRepo.findAll();
//        List<CarDTO> carDTOS = new ArrayList<>();
//        for (Car car:cars
//             ) {
//            Car foundedCar = carRepo.getById(car.getId());
//
//        }

        return cars;
    }
}
