package com.example.cabinetmedical.exception;

public class SecretaireNotFoundException extends RuntimeException {
    public SecretaireNotFoundException(String message) {
        super(message);
    }
}
