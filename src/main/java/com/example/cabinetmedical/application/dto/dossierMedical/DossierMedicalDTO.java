package com.example.cabinetmedical.application.dto.dossierMedical;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.example.cabinetmedical.application.DTO.AllergieDTO;
import com.example.cabinetmedical.application.DTO.AntecedentChirurgicalDTO;
import com.example.cabinetmedical.application.DTO.AntecedentMedicalDTO;
import com.example.cabinetmedical.application.DTO.HabitudeVieDTO;
import com.example.cabinetmedical.application.DTO.TraitementChroniqueDTO;
import com.example.cabinetmedical.application.dto.patient.PatientDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DossierMedicalDTO {
    private Integer idDossier;
    private PatientDTO patient ; 
    private Date dateCreation  ; 



    private List<AntecedentChirurgicalDTO> antecedentChirurgicals ; 
    private List<AntecedentMedicalDTO>    antecedentMedicals ; 
    private List<AllergieDTO> allergies ; 
    private List<TraitementChroniqueDTO> traitementChroniques ; 
    private HabitudeVieDTO habitudeVie ; 
}
