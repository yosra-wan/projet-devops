package com.example.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "st_enterprise")
public class STEnterprise extends STUser {

    private String matriculeFiscale;
    private String address;


    //liste voiture
}
