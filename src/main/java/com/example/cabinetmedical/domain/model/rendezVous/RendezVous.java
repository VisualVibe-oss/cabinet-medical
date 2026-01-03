package com.example.cabinetmedical.domain.model.rendezVous;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;

import java.util.Date;

public class RendezVous {
    private int idRendezVous;
    private Date dateRendezVous;
    private String motif;
    private String statut;
    private String notes;
    private Cabinet cabinet;
    private Patient patient;

    public RendezVous() {
    }

    public RendezVous(int idRendezVous, Date dateRendezVous, String motif, String statut,
                      String notes, Cabinet cabinet, Patient patient) {
        this.idRendezVous = idRendezVous;
        this.dateRendezVous = dateRendezVous;
        this.motif = motif;
        this. statut = statut;
        this.notes = notes;
        this.cabinet = cabinet;
        this.patient = patient;
    }

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
}
