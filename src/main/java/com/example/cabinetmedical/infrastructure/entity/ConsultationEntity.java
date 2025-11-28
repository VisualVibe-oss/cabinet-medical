package com.example.cabinetmedical.infrastructure.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "consultation")
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsultation;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String examenClinique;

    @Column(nullable = false)
    private String examenSupplementaire;

    @Column(nullable = false)
    private String diagnostic;

    @Column(nullable = false)
    private String observations;

    @Column(nullable = false)
    private Boolean payement;

    @OneToOne
    @JoinColumn(name="idRendezVous")
    private RendezVousEntity rendezVous;

    @ManyToOne
    @JoinColumn(name="idDossierMedical")
    private  DossierMedicalEntity dossierMedical;

    @OneToOne
    @JoinColumn(name = "idOrdonnanceSup")
    private OrdonnanceSupEntity ordonnanceSup;

    @OneToOne
    @JoinColumn(name = "idOrdonnanceMed")
    private OrdonnanceMedEntity ordonnanceMed;

    public ConsultationEntity(int idConsultation, String type, Date date, String examenClinique, String examenSupplementaire, String diagnostic, String observations, Boolean payement, RendezVousEntity rendezVous, DossierMedicalEntity dossierMedical, OrdonnanceSupEntity ordonnanceSup, OrdonnanceMedEntity ordonnanceMed) {
        this.idConsultation = idConsultation;
        this.type = type;
        this.date = date;
        this.examenClinique = examenClinique;
        this.examenSupplementaire = examenSupplementaire;
        this.diagnostic = diagnostic;
        this.observations = observations;
        this.payement = payement;
        this.rendezVous = rendezVous;
        this.dossierMedical = dossierMedical;
        this.ordonnanceSup = ordonnanceSup;
        this.ordonnanceMed = ordonnanceMed;
    }

    public ConsultationEntity(){}

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExamenClinique() {
        return examenClinique;
    }

    public void setExamenClinique(String examenClinique) {
        this.examenClinique = examenClinique;
    }

    public String getExamenSupplementaire() {
        return examenSupplementaire;
    }

    public void setExamenSupplementaire(String examenSupplementaire) {
        this.examenSupplementaire = examenSupplementaire;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Boolean getPayement() {
        return payement;
    }

    public void setPayement(Boolean payement) {
        this.payement = payement;
    }

    public RendezVousEntity getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVousEntity rendezVous) {
        this.rendezVous = rendezVous;
    }

    public DossierMedicalEntity getDossierMedical() {
        return dossierMedical;
    }

    public void setDossierMedical(DossierMedicalEntity dossierMedical) {
        this.dossierMedical = dossierMedical;
    }

    public OrdonnanceSupEntity getOrdonnanceSup() {
        return ordonnanceSup;
    }

    public void setOrdonnanceSup(OrdonnanceSupEntity ordonnanceSup) {
        this.ordonnanceSup = ordonnanceSup;
    }

    public OrdonnanceMedEntity getOrdonnanceMed() {
        return ordonnanceMed;
    }

    public void setOrdonnanceMed(OrdonnanceMedEntity ordonnanceMed) {
        this.ordonnanceMed = ordonnanceMed;
    }
}
