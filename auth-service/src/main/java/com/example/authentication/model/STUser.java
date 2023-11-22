package com.example.authentication.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@ToString(exclude = "permissions")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class STUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstname;

    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String phone;

    private String password;

    private boolean state;


    @OneToMany(mappedBy = "user", targetEntity = Permission.class)
    private List<Permission> permissions;


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));

}

    @Override
    public String getUsername() {
        return username;
    }

@Override
public boolean isAccountNonExpired() {
 return true;
//   throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
}

@Override
public boolean isAccountNonLocked() {
    return true;
//    throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
//    throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
}

@Override
public boolean isEnabled() {
    return true;
//    throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
}

}
