package com.example.authentication.repository;

import com.example.authentication.model.STDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<STDriver, Integer> {
}
