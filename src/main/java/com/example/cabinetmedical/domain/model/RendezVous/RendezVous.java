package com.example.cabinetmedical.domain.model.RendezVous;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.RendezVousState;

import java.time.LocalDateTime;
import java.util.Date;

public class RendezVous {
    private int idRendezVous;
    private LocalDateTime dateDebutRendezVous;
    private LocalDateTime dateFinRendezVous;
    private String motif;
    private RendezVousState statut;
    private String notes;
    private Cabinet cabinet;
    private String consultationType;
    private Patient patient;
    private int prix;

    public RendezVous(int idRendezVous, LocalDateTime dateDebutRendezVous, LocalDateTime dateFinRendezVous, String motif,  RendezVousState statut, String notes, Cabinet cabinet, Patient patient, int prix) {
        this.idRendezVous = idRendezVous;
        this.dateDebutRendezVous = dateDebutRendezVous;
        this.dateFinRendezVous = dateFinRendezVous;
        this.consultationType = consultationType;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
        this.cabinet = cabinet;
        this.patient = patient;
        this.prix = prix;
    }
    public RendezVous(){}

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

   public String getConsultationType() {
        return consultationType;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }   
    public int getPrix() {
        return prix;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public RendezVousState getStatut() {
        return statut;
    }

    public void setStatut(RendezVousState statut) {
        this.statut = statut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
}