package com.example.CarManagement.controller;

import com.example.CarManagement.exception.AlreadyExistsException;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.repository.CarRepo;
import com.example.CarManagement.service.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
@CrossOrigin("*")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @PostMapping(value = "/add")
    public ResponseEntity<Object> addCar(@RequestBody Car car) {
        try {
            carService.createCar(car);
            return ResponseEntity.ok("Car added successfully.");
        }catch (AlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car already exists.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding car.");
        }

    }

    @GetMapping(value = "/all-cars")
    public ResponseEntity<Object> getAllCars(){
        try {
            carService.getAllCars();
            return ResponseEntity.accepted().body(carService.getAllCars());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error getting cars.") ;
        }
    }
}
