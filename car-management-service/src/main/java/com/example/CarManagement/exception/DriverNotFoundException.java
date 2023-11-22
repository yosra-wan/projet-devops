package com.example.CarManagement.exception;

public class DriverNotFoundException extends Exception {
    public DriverNotFoundException() {super();}

    public DriverNotFoundException(String msg) {
        super(msg);
    }
}
