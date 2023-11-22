package com.example.authentication.controller;

import com.example.authentication.model.Screen;
import com.example.authentication.service.IScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ScreenController {

    @Autowired
    private IScreenService screenService;


    @PostMapping("add")
    public ResponseEntity<Screen> createScreen(@RequestBody Screen request ){
        String label = request.getLabel();

        Screen newScreen = screenService.createScreen(label);
        return new ResponseEntity<>(newScreen, HttpStatus.CREATED);
    }
}
