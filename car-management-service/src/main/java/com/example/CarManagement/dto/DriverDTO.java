package com.example.CarManagement.dto;

import com.example.CarManagement.model.CarHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String phone;
    private Double hourRate;
    private List<CarHistoryDTO> carHistories;
}
