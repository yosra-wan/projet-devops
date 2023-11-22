package com.example.authentication.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "carHistories")
@Entity
@Table(name = "st_driver")
public class STDriver extends STUser {

        private Double hourRate;


}
