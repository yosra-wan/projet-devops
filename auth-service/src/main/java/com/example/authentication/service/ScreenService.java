package com.example.authentication.service;

import com.example.authentication.model.Screen;
import com.example.authentication.repository.ScreenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService implements IScreenService {

    @Autowired
    private ScreenRepo screenRepo;

    @Override
    public Screen createScreen(String label) {

        Screen screen = new Screen();
        screen.setLabel(label);

        return screenRepo.save(screen);
    }

    @Override
    public List<Screen> findAll() {
        return screenRepo.findAll();
    }

}
