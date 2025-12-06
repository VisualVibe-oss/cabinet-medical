package com.example.cabinetmedical.exception;

public class PackNotRegistredError extends RuntimeException {
    public PackNotRegistredError(String message){
        super(message) ;
    }
}
