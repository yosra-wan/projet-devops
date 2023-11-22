package com.example.authentication.model;

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
public class PermissionId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "screen_id")
    private Integer screenId;

}
