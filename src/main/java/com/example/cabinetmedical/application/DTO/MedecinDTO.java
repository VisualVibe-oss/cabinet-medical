package com.example.cabinetmedical.application.dto;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

import lombok.Data;

@Data
public class MedecinDTO {
    private int idMedecin;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String signature;
    private String telephone;
    private Cabinet cabinet;
}
