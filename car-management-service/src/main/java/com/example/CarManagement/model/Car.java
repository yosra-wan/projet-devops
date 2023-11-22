package com.example.CarManagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "carHistories")
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String make;
    private String model;
    private Date manufacturingDate;
    private Integer seatsNumber;
    // private Integer lat;
    // private Integer longi;
    @Column(unique = true)
    private String registrationNumber;

//    @JsonIgnore
    @OneToMany(mappedBy = "car", targetEntity = CarHistory.class)
    private List<CarHistory> carHistories;
}
