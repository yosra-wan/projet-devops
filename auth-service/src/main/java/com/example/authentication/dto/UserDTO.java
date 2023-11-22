package com.example.authentication.dto;

import com.example.authentication.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String phone;

    private List<permissionDTO> permissions;


}
