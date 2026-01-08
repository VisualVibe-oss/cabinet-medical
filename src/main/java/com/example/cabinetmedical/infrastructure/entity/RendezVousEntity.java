package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.cabinetmedical.domain.utils.RendezVousState;

@Entity
@Table(name = "rendezVous")
public class RendezVousEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRendezVous;

    @Column(nullable = false)
    private LocalDateTime dateDebutRendezVous;

    @Column(nullable = true)
    private LocalDateTime dateFinRendezVous;

    @Column(nullable = false)
    private String motif;

    @Column(nullable = false)
    private int prix;

    @Column(nullable = true)
    private String notes;

    @Column(nullable = true)
    private String consultationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RendezVousState statut;

    @OneToOne(mappedBy = "rendezVous") // Corrigé ici
    private ConsultationEntity consultation;

    @ManyToOne
    @JoinColumn(name = "idCabinet")
    private CabinetEntity cabinet;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private PatientEntity patient;

    // ==========================================
    // 1. CONSTRUCTEUR VIDE (Obligatoire pour JPA)
    // ==========================================
    public RendezVousEntity() {
    }

    // ==========================================
    // 2. CONSTRUCTEUR AVEC TOUS LES ATTRIBUTS
    // ==========================================
    public RendezVousEntity(int idRendezVous, LocalDateTime dateDebutRendezVous, LocalDateTime dateFinRendezVous, 
                            String motif, int prix, String notes, String consultationType, 
                            RendezVousState statut, ConsultationEntity consultation, 
                            CabinetEntity cabinet, PatientEntity patient) {
        this.idRendezVous = idRendezVous;
        this.dateDebutRendezVous = dateDebutRendezVous;
        this.dateFinRendezVous = dateFinRendezVous;
        this.motif = motif;
        this.prix = prix;
        this.notes = notes;
        this.consultationType = consultationType;
        this.statut = statut;
        this.consultation = consultation;
        this.cabinet = cabinet;
        this.patient = patient;
    }

    // ==========================================
    // 3. GETTERS ET SETTERS
    // ==========================================

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public LocalDateTime getDateDebutRendezVous() {
        return dateDebutRendezVous;
    }

    public void setDateDebutRendezVous(LocalDateTime dateDebutRendezVous) {
        this.dateDebutRendezVous = dateDebutRendezVous;
    }

    public LocalDateTime getDateFinRendezVous() {
        return dateFinRendezVous;
    }

    public void setDateFinRendezVous(LocalDateTime dateFinRendezVous) {
        this.dateFinRendezVous = dateFinRendezVous;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public RendezVousState getStatut() {
        return statut;
    }

    public void setStatut(RendezVousState statut) {
        this.statut = statut;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
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
}