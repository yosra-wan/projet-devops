package com.example.CarManagement.repository;

import com.example.CarManagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    Car findByRegistrationNumber(String registrationNumber);
}
