package com.example.cabinetmedical.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String signature;
    private String telephone;
    private String role;
    private String message; //succes ou erreur
    private String token;
}
