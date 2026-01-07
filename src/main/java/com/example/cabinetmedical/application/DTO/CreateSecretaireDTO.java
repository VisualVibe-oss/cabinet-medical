package com.example.cabinetmedical.application.dto;

import lombok.Data;

@Data
public class CreateSecretaireDTO {
    SecretaireDTO secretaire;
    String email;
    String password;
}
