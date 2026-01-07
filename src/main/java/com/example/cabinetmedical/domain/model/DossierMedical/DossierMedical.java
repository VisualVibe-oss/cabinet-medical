package com.example.cabinetmedical.domain.model.DossierMedical;

import com.example.cabinetmedical.domain.model.Allergie.Allergie;
import com.example.cabinetmedical.domain.model.AntecedantMedical.AntecedentMedical;
import com.example.cabinetmedical.domain.model.AntecedentChirurgical.AntecedentChirurgical;
import com.example.cabinetmedical.domain.model.HabitudeVie.HabitudeVie;
import com.example.cabinetmedical.domain.model.TraitementChronique.TraitementChronique;
import com.example.cabinetmedical.domain.model.consultation.Consultation;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DossierMedical {
    private Integer idDossier;

    
    private String Status ;

    private Date dateCreation;
    private Patient patient;
    private List<Consultation> consultation;

    private List<AntecedentChirurgical> antecedentChirurgicals ; 
    private List<AntecedentMedical>    antecedentMedicals ; 
    private List<Allergie> allergies ; 
    private List<TraitementChronique> traitementChroniques ; 
    private HabitudeVie habitudeVie ; 
    
    
    
}
