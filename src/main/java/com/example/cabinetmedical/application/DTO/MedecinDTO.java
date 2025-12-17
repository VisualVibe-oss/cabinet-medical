package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

import lombok.Data;

import java.util.List;

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
