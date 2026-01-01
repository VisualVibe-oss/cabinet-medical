package com.example.cabinetmedical.application.DTO;


import java.util.Date;
import java.util.List;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;

import lombok.Data;

@Data
public class PatientDTO {
    private int idPatient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String sexe;
    private Date dateNaissance;
    private String typeMutuelle;

    // Objets imbriqués (utilisant des DTOs simplifiés)
    private Cabinet cabinet;
    private  DossierMedical dossierMedical;

}