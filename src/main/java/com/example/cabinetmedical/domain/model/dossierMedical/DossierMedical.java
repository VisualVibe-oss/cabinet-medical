package com.example.cabinetmedical.domain.model.dossierMedical;

import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;

import java.util.Date;
import java.util.List;

public class DossierMedical {
    private Integer idDossier;
    private String antMedicaux;
    private String antChirug;
    private String allergies;
    private String traitement;
    private String habitudes;
    private String historiqueConsultations;
    private String documentsMedicaux;
    private Date dateCreation;
    private Integer patient;
    private List<Integer> consultation;


    public DossierMedical(Integer idDossier, String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, String historiqueConsultations, String documentsMedicaux, Date dateCreation, Integer patient, List<Integer> consultation) {
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

    public Integer getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(Integer idDossier) {
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

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public List<Integer> getConsultation() {
        return consultation;
    }

    public void setConsultation(List<Integer> consultation) {
        this.consultation = consultation;
    }
}
