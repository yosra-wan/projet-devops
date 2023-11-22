package com.example.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "screen"})
@Entity
@Table(name = "permissions")
public class Permission {

    @EmbeddedId
    private PermissionId id;

    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    @JsonIgnoreProperties("permissions")
    private STUser user;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    @MapsId("screenId")
    @JsonIgnoreProperties("permissions")
    private Screen screen;
}
