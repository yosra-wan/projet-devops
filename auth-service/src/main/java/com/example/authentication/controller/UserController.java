package com.example.authentication.controller;

import com.example.authentication.dto.DriverDTO;
import com.example.authentication.dto.LoginDTO;
import com.example.authentication.exception.STUserNotFoundException;
import com.example.authentication.model.STAdmin;
import com.example.authentication.model.STDriver;
import com.example.authentication.model.STEnterprise;
import com.example.authentication.model.STUser;
import com.example.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody STAdmin user) {
        try {
            Map<String, String> response = new HashMap<>();
            String jwt = userService.register(user);
            response.put("token",jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error code 4005 : Bad credentials ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add-driver", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addDriver(@RequestBody STDriver user) {
        try {
//            Map<String, String> response = new HashMap<>();
//            String jwt = userService.addDriver(user);
//            response.put("token",jwt);
           DriverDTO driver= userService.addDriver(user);
            return new ResponseEntity<>(driver,HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error code 4005 : Bad credentials ", HttpStatus.BAD_REQUEST);
        }
    }


//  @PreAuthorize("principal.getClass().getSimpleName() == 'STAdmin'")
    @PostMapping(value = "/add-enterprise", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addEnterprise(@RequestBody STEnterprise user) {
        try {
//            Map<String, String> response = new HashMap<>();
//            String jwt = userService.addEnterprise(user);
//            response.put("token",jwt);
            userService.addEnterprise(user);
            return new ResponseEntity<>("user Added successfully",HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error code 4005 : Bad credentials ", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO user) {
        try {
            Map<String, String> response = new HashMap<>();
            String jwt = userService.login(user);
            response.put("token",jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (STUserNotFoundException e) {
            return new ResponseEntity<>("Error code 4004 : User not found ", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error code 4005 : Bad credentials ", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/is-admin")
    public ResponseEntity<Boolean> verifyAdmin(@RequestParam String email) throws STUserNotFoundException {
        try {
            boolean isAdmin = userService.isAdmin(email);
            return ResponseEntity.ok(isAdmin);
        } catch (STUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping("/is-driver")
    public ResponseEntity<Boolean> verifDriver(@RequestParam String email) throws STUserNotFoundException {
        try {
            boolean isDriver = userService.isDriver(email);
            return ResponseEntity.ok(isDriver);
        } catch (STUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

        @GetMapping("/is-entreprise")
    public ResponseEntity<Boolean> verifEntreprise(@RequestParam String email) throws STUserNotFoundException {
        try {
            boolean isEntreprise = userService.isEntreprise(email);
            return ResponseEntity.ok(isEntreprise);
        } catch (STUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping("/getAllUsers")
    public List<STUser> getUsers() {
        return userService.getAllUsers();
    }



}
