package com.example.authentication.service;

import com.example.authentication.model.Screen;

import java.util.List;

public interface IScreenService {

    Screen createScreen(String label);

    List<Screen> findAll();
}
