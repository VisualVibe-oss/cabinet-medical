package com.example.cabinetmedical.application.DTO.patient;

import com.example.cabinetmedical.application.DTO.CabinetDTO;
import com.example.cabinetmedical.application.DTO.dossierMedical.DossierMedicalDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Integer idPatient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String sexe;
    private Date dateNaissance;
    private String typeMutuelle;
    private DossierMedicalDTO dossierMedical;
    private CabinetDTO cabinet;
}
