package com.example.cabinetmedical.domain.model.patient;


import java.util.Date;
import java.util.List;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class Patient {
    private Integer idPatient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String sexe;
    private Date dateNaissance;
    private String typeMutuelle;
    private Cabinet cabinet;
    private DossierMedical DossierMedical;
    private List<RendezVous> rendezVous; 



}
