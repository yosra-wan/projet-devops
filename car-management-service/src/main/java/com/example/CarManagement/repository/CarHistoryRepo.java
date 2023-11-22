package com.example.CarManagement.repository;

import com.example.CarManagement.model.Car;
import com.example.CarManagement.model.CarHistory;
import com.example.CarManagement.model.CarHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarHistoryRepo extends JpaRepository<CarHistory, CarHistoryId> {
    List<CarHistory> findById_DriverId(Integer driverId);
}
