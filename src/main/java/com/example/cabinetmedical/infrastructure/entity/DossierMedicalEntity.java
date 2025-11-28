package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="dossierMedical")
public class DossierMedicalEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idDossier;

    @Column(nullable = false)
    private String antMedicaux;

    @Column(nullable = false)
    private String antChirug;

    @Column(nullable = false)
    private String allergies;

    @Column(nullable = false)
    private String traitement;

    @Column(nullable = false)
    private String habitudes;

    @Column(nullable = false)
    private String historiqueConsultations;

    @Column(nullable = false)
    private String documentsMedicaux;

    @Column(nullable = false)
    private Date dateCreation;

    @OneToOne(mappedBy = "dossierMedical")
    private PatientEntity patient;

    @OneToMany(mappedBy = "dossierMedical")
    private List<ConsultationEntity> consultation;

    public DossierMedicalEntity() {
    }

    public DossierMedicalEntity(int idDossier, String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, String historiqueConsultations, String documentsMedicaux, Date dateCreation, PatientEntity patient, List<ConsultationEntity> consultation) {
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

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public List<ConsultationEntity> getConsultation() {
        return consultation;
    }

    public void setConsultation(List<ConsultationEntity> consultation) {
        this.consultation = consultation;
    }


}
