package com.example.cabinetmedical.domain.model.DossierMedical;

import com.example.cabinetmedical.domain.model.Consultation.Consultation;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;


import java.util.Date;
import java.util.List;

public class DossierMedical {
    private int idDossier;

    private String antMedicaux;
    private String antChirug;
    private String allergies;
    private String traitement;
    private String habitudes;
    private String historiqueConsultations;
    private String documentsMedicaux;
    private Date dateCreation;
    private Patient patient;
    private List<Consultation> consultation;
    public DossierMedical(int idDossier, String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, String historiqueConsultations, String documentsMedicaux, Date dateCreation, Patient patient, List<Consultation> consultation) {
        this.idDossier = idDossier;
        this.antMedicaux = antMedicaux;
        this.antChirug = antChirug;
        this.allergies = allergies;
        this.traitement = traitement;
        this.habitudes = habitudes;
        this.historiqueConsultations = historiqueConsultations;
        this.documentsMedicaux = documentsMedicaux;
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

    public String getAntMedicaux() {
        return antMedicaux;
    }

    public void setAntMedicaux(String antMedicaux) {
        this.antMedicaux = antMedicaux;
    }

    public String getAntChirug() {
        return antChirug;
    }

    public void setAntChirug(String antChirug) {
        this.antChirug = antChirug;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getHabitudes() {
        return habitudes;
    }

    public void setHabitudes(String habitudes) {
        this.habitudes = habitudes;
    }

    public String getHistoriqueConsultations() {
        return historiqueConsultations;
    }

    public void setHistoriqueConsultations(String historiqueConsultations) {
        this.historiqueConsultations = historiqueConsultations;
    }

    public String getDocumentsMedicaux() {
        return documentsMedicaux;
    }

    public void setDocumentsMedicaux(String documentsMedicaux) {
        this.documentsMedicaux = documentsMedicaux;
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
