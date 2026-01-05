package com.example.cabinetmedical.exception;

public class OverlappedAppointmentException extends RuntimeException {
    public OverlappedAppointmentException(String message) {
        super(message);
    }
}
