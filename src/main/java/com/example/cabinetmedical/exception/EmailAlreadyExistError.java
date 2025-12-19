package com.example.cabinetmedical.exception;





public class EmailAlreadyExistError extends RuntimeException{
    public EmailAlreadyExistError(String message) {
        super(message);
    }
    
}
