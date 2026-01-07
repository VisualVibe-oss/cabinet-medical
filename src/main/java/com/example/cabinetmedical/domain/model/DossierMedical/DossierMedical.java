package com.example.cabinetmedical.domain.model.DossierMedical;

import com.example.cabinetmedical.domain.model.consultation.Consultation;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;


import java.util.Date;
import java.util.List;

public class DossierMedical {
    private int idDossier;

    
    private String Status ;

    private Date dateCreation;
    private Patient patient;
    private List<Consultation> consultation;
    public DossierMedical(int idDossier, String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, String historiqueConsultations, String documentsMedicaux, Date dateCreation, Patient patient, List<Consultation> consultation) {
        this.idDossier = idDossier;
        this.dateCreation = dateCreation;
        this.patient = patient;
        this.consultation = consultation;
    }

    public int getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(int idDossier) {
        this.idDossier = idDossier;
    }

    


    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Consultation> getConsultation() {
        return consultation;
    }

    public void setConsultation(List<Consultation> consultation) {
        this.consultation = consultation;
    }
}
