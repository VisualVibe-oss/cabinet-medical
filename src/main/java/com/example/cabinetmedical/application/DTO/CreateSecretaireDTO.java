package com.example.cabinetmedical.application.DTO;

import lombok.Data;

@Data
public class CreateSecretaireDTO {
    SecretaireDTO secretaire;
    String email;
    String password;
}
