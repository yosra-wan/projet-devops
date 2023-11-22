package com.example.authentication.service;

import com.example.authentication.dto.DriverDTO;
import com.example.authentication.exception.STUserNotFoundException;

public interface IDriverService {

    DriverDTO getDriverById(Integer id) throws STUserNotFoundException;

}
