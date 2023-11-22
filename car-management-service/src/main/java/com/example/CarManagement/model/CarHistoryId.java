package com.example.CarManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CarHistoryId implements Serializable {
    @Column(name="car_id")
    private Integer carId;

    @Column(name = "driver_id")
    private Integer driverId;
}
