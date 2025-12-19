package com.example.cabinetmedical.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedecinInscriptionRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String signature;

    private String telephone;

    private String nomCabinet;
    private String specialite;
    private String adresseCabinet;
    private String telCabinet;
    private String logoCabinet;
}
