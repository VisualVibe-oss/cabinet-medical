package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rendezVous")
public class RendezVousEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRendezVous;

    @Column(nullable = false)
    private Date dateRendezVous;

    @Column(nullable = false)
    private String motif;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private String notes;

    @ManyToOne
    @JoinColumn(name="idCabinet")
    private CabinetEntity cabinet;

    @ManyToOne
    @JoinColumn(name="idPatient")
    private PatientEntity patient;

    @OneToOne(mappedBy = "rendezVous")
    private ConsultationEntity consultation;

    public RendezVousEntity(int idRendezVous, Date dateRendezVous, String motif, String statut, String notes, CabinetEntity cabinet, PatientEntity patient, ConsultationEntity consultation) {
        this.idRendezVous = idRendezVous;
        this.dateRendezVous = dateRendezVous;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
        this.cabinet = cabinet;
        this.patient = patient;
        this.consultation = consultation;
    }
    public RendezVousEntity(){}

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }
}
