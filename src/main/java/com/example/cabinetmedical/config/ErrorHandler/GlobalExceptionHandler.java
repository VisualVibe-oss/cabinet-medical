package com.example.cabinetmedical.config.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.exception.CredentialNotValidError;
import com.example.cabinetmedical.exception.EmailAlreadyExistError;
import com.example.cabinetmedical.exception.PhoneNumbreAlreadyExistError;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(EmailAlreadyExistError.class)
        ResponseEntity<ApiResponse<String>> handleEmailAlreadyExistError(EmailAlreadyExistError ex,
                        HttpServletRequest req) {

                ApiResponse<String> response = new ApiResponse<>();
                ApiResponse.builder()
                                .data(ex.getMessage())
                                .message("Erreur lors de la creation du comptes.")
                                .status(HttpStatus.CONFLICT.value())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(response);
        }

        @ExceptionHandler(PhoneNumbreAlreadyExistError.class)
        ResponseEntity<ApiResponse<String>> handlePhoneNumberAlreadyExistError(EmailAlreadyExistError ex,
                        HttpServletRequest req) {

                ApiResponse<String> response = new ApiResponse<>();
                ApiResponse.builder()
                                .data(ex.getMessage())
                                .message("Erreur lors de la creation du comptes.")
                                .status(HttpStatus.CONFLICT.value())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(response);
        }

        @ExceptionHandler(CredentialNotValidError.class)
        public ResponseEntity<ApiResponse<String>> handleCredentialNotValidError(
                        CredentialNotValidError ex, HttpServletRequest req) {

                ApiResponse<String> response = ApiResponse.<String>builder()
                                .data(ex.getMessage())
                                .message("Erreur lors de la connexion.")
                                .status(HttpStatus.UNAUTHORIZED.value())
                                .success(false)
                                .build();

                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(response);
        }

        @ExceptionHandler(BadCredentialsException.class)
        ResponseEntity<ApiResponse<String>> handleNoneValideRefreshToken(BadCredentialsException ex,
                        HttpServletRequest req) {

                ApiResponse<String> response = new ApiResponse<>();
                ApiResponse.builder()
                                .data(ex.getMessage())
                                .message("Refresh token n'est pas verifier")
                                .status(HttpStatus.UNAUTHORIZED.value())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(response);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex,
                        HttpServletRequest req) {

                ApiResponse<String> response = ApiResponse.<String>builder()
                                .data(null)
                                .message(ex.getMessage()) // Message explicite (ex: "Rendez-vous non trouvé")
                                .status(HttpStatus.NOT_FOUND.value()) // 404
                                .build();

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(response);
        }

}
