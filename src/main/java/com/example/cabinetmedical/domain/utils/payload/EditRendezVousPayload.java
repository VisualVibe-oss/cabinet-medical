package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.RendezVousState;

import java.time.LocalDateTime;
import java.util.List;

public class EditRendezVousPayload {
    private RendezVous current;
    private LocalDateTime dateDebutRendezVous;
    private LocalDateTime dateFinRendezVous;
    private String motif;
    private RendezVousState statut;
    private String notes;
    private Cabinet cabinet;
    private Patient patient;
    private List<RendezVous> existingrvs;

    public EditRendezVousPayload(RendezVous current, LocalDateTime dateDebutRendezVous, LocalDateTime dateFinRendezVous, String motif, RendezVousState statut, String notes, Cabinet cabinet, Patient patient, List<RendezVous> existingrvs) {
        this.current = current;
        this.dateDebutRendezVous = dateDebutRendezVous;
        this.dateFinRendezVous = dateFinRendezVous;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
        this.cabinet = cabinet;
        this.patient = patient;
        this.existingrvs = existingrvs;
    }

    public RendezVous getCurrent() {
        return current;
    }

    public void setCurrent(RendezVous current) {
        this.current = current;
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

    public List<RendezVous> getExistingrvs() {
        return existingrvs;
    }

    public void setExistingrvs(List<RendezVous> existingrvs) {
        this.existingrvs = existingrvs;
    }
}
