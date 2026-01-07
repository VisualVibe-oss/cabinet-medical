package com.example.cabinetmedical.domain.model.DossierMedical;

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
    
}
