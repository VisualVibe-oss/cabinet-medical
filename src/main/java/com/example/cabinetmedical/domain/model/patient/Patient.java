package com.example.cabinetmedical.domain.model.patient;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.dossierMedical.DossierMedical;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;

import java.util.Date;

public class Patient {
    private Integer idPatient;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String sexe;
    private Date dateNaissance;
    private String typeMutuelle;
    private Cabinet cabinet;
    private DossierMedical DossierMedical;

    public Patient(Integer idPatient, String nom, String prenom, String cin, String telephone, String sexe, Date dateNaissance, String typeMutuelle, Cabinet cabinet, DossierMedical DossierMedical) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.typeMutuelle = typeMutuelle;
        this.cabinet = cabinet;
        this.DossierMedical = DossierMedical;
    }

    public Patient() {

    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTypeMutuelle() {
        return typeMutuelle;
    }

    public void setTypeMutuelle(String typeMutuelle) {
        this.typeMutuelle = typeMutuelle;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public DossierMedical getDossierMedical() {
        return DossierMedical;
    }

    public void setDossierMedical(DossierMedical idDossierMedical) {
        this.DossierMedical = idDossierMedical;
    }
}
