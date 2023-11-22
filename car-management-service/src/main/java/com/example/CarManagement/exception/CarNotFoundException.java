package com.example.CarManagement.exception;

public class CarNotFoundException extends Exception {

    public CarNotFoundException() {super();}

    public CarNotFoundException(String msg) {
        super(msg);
    }
}
