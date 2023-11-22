package com.example.authentication.controller;

import com.example.authentication.dto.DriverDTO;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.STDriver;
import com.example.authentication.model.STUser;
import com.example.authentication.service.IDriverServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DriverController {

    @Autowired
    private IDriverServiceImpl driverService;


    @GetMapping("/driver-by-id")
    public ResponseEntity<Object> getDriverById(@RequestParam Integer id) {
       try{
           DriverDTO driver = driverService.getDriverById(id);
           return ResponseEntity.ok(driver);
       }catch (STUserNotFoundException e) {
           return new ResponseEntity<>("Error code 4004 : User not found ", HttpStatus.BAD_REQUEST);
       }catch (Exception e){
           return new ResponseEntity<>("Error code 4007 : this user is not driver", HttpStatus.BAD_REQUEST);

       }
    }

}
