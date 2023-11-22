package com.example.authentication.controller;

import com.example.authentication.dto.PermissionHandler;
import com.example.authentication.model.Permission;
import com.example.authentication.model.STUser;
import com.example.authentication.repository.UserRepo;
import com.example.authentication.service.PermissionServiceImpl;
import com.example.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/update")
    public ResponseEntity<Object> updatePermissions(@RequestParam Integer userId, @RequestParam String label, @RequestBody PermissionHandler permHand) {
       try{
           Permission permission = permissionService.updatePermissions(userId, label, permHand);

                   return new ResponseEntity<>("Permissions updated successfully", HttpStatus.OK);
       }
       catch (Exception e){
           return new ResponseEntity<>("Error code 4006 : Bad request ", HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/permissionsByUser")
    public ResponseEntity<Map<String, Object>> getPermissionsByUser(@RequestParam Integer userId) {
        STUser user = userRepo.findById(userId).get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Map<String, Object> permissions = permissionService.getPermissionsByUser(user);
        return ResponseEntity.ok(permissions);
    }

}
