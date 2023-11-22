package com.example.authentication.service;

import com.example.authentication.dto.DriverDTO;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.STDriver;
import com.example.authentication.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDriverServiceImpl implements IDriverService {

    @Autowired
    private DriverRepo driverRepo;

    @Override
    public DriverDTO getDriverById(Integer id) throws STUserNotFoundException {
      STDriver driver = driverRepo.findById(id).get();
      DriverDTO driverDto = new DriverDTO(driver.getId(), driver.getFirstname(), driver.getLastname(), driver.getEmail(), driver.getUsername(), driver.getPhone(), driver.getHourRate());

      if(driver == null){
          throw new STUserNotFoundException();
      }
        return driverDto;
    }
}
