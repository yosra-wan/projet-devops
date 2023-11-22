package com.example.CarManagement.service;

import com.example.CarManagement.dto.CarDTO;
import com.example.CarManagement.dto.CarListDTO;
import com.example.CarManagement.dto.DriverDTO;
import com.example.CarManagement.exception.CarNotFoundException;
import com.example.CarManagement.exception.DriverNotFoundException;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.CarHistory;
import com.example.CarManagement.model.CarHistoryId;
import com.example.CarManagement.repository.CarHistoryRepo;
import com.example.CarManagement.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarHistoryServImpl implements ICarHistoryServImpl {

    @Autowired
    private CarHistoryRepo carHistoryRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void createCarHistory(CarDTO carDTO) throws CarNotFoundException, DriverNotFoundException {

        // FIXME : error message
        DriverDTO driverResponse = restTemplate.getForEntity(
                "http://localhost:8080/api/driver/driver-by-id?id=" + carDTO.getCurrentUserId(),
                DriverDTO.class
        ).getBody();
        System.out.println("driver"+driverResponse);
        if (driverResponse == null) {
            throw new DriverNotFoundException("Driver not found");
        }

        Car existingCar = carRepo.findById(carDTO.getId()).orElse(null);
        if (existingCar == null) {
            throw new CarNotFoundException("No car found with ID: " + carDTO.getId());
        }

        CarHistoryId id = new CarHistoryId();
        id.setCarId(carDTO.getId());
        id.setDriverId(carDTO.getCurrentUserId());
        CarHistory carH = new CarHistory();
        carH.setId(id);
        carH.setCurrentCar(true);
        carH.setCar(existingCar);
        carHistoryRepo.save(carH);

    }

    @Override
    public List<CarListDTO> getCarsByDriverId(Integer id) {

        List<CarListDTO> carList = new ArrayList<>();
        List<CarHistory> hist_cars = carHistoryRepo.findById_DriverId(id);
        for (CarHistory carHist : hist_cars ) {
            Car foundedCar = carRepo.findById(carHist.getCar().getId()).orElse(null);
            if(foundedCar != null){
                CarListDTO car = new CarListDTO();
                car.setMake(foundedCar.getMake());
                car.setModel(foundedCar.getModel());
                car.setSeatsNumber(foundedCar.getSeatsNumber());
                car.setRegistrationNumber(foundedCar.getRegistrationNumber());
                car.setCurrentCar(carHist.isCurrentCar());
                car.setManufacturingDate(foundedCar.getManufacturingDate());

                carList.add(car);
            }
        }
        return carList;
    }
}
