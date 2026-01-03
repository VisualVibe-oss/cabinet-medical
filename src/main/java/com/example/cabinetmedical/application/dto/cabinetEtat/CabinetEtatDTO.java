package com.example.cabinetmedical.application.dto.cabinetEtat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinetEtatDTO {
    private Integer id;
    private String nom;
    private String specialite;
    private String adresse;
    private String telephone;
    private Boolean estActif;
}
