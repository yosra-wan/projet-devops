package com.example.CarManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@ToString(exclude = {"car"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_history")
public class CarHistory {

    @EmbeddedId
    private CarHistoryId id;

    private boolean currentCar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @MapsId("carId")
   @JsonIgnore
    private Car car;



}
