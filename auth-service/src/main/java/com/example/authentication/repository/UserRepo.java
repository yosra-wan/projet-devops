package com.example.authentication.repository;

import com.example.authentication.model.STUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<STUser, Integer> {
    STUser findByEmail(String email);

//    STUser findByEmailOrPhoneOrUsername(String email, String phone, String username);

    STUser findByPhone(String phone);

    STUser findByUsername(String username);

}
