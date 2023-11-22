package com.example.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String phone;
    private Double hourRate;
}
