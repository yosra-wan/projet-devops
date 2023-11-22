package com.example.CarManagement.controller;

import com.example.CarManagement.dto.CarDTO;
import com.example.CarManagement.dto.CarListDTO;
import com.example.CarManagement.exception.CarNotFoundException;
import com.example.CarManagement.exception.DriverNotFoundException;
import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.CarHistory;
import com.example.CarManagement.service.CarHistoryServImpl;
import com.example.CarManagement.service.ICarHistoryServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car/history")
@CrossOrigin("*")
public class CarHistController {

    @Autowired
    private ICarHistoryServImpl iCarHistoryServ;

    @PostMapping("/create")
    public ResponseEntity<Object> createHistory(@RequestBody CarDTO carDTO){
       try{
           iCarHistoryServ.createCarHistory(carDTO);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (CarNotFoundException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car not found with ID: " + carDTO.getId());
       } catch (DriverNotFoundException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver not found or invalid.");
       }
    }

    @GetMapping("/by-driver")
    public ResponseEntity<Object> getCarsByDriverId(@RequestParam Integer driverId) {
        List<CarListDTO> cars = iCarHistoryServ.getCarsByDriverId(driverId);

        return  ResponseEntity.ok(cars);
    }

}
