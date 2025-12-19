package com.example.cabinetmedical.domain.model.Person;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;

import java.util.Date;
import java.util.List;

public abstract class Person {
    private int idPatient;
    private String nom;
    private String prenom;
    private String cin;
    private String password;
    private String telephone;
    private String sexe;
    private Date dateNaissance;
    private String typeMutuelle;

    private Cabinet cabinet;
    private List<RendezVous> rendezVous;




    public Person(int idPatient, String nom, String prenom, String cin, String password, String telephone, String sexe, Date dateNaissance, String typeMutuelle, Cabinet cabinet, List<RendezVous> rendezVous) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.password = password;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.typeMutuelle = typeMutuelle;
        this.cabinet = cabinet;
        this.rendezVous = rendezVous;
    }

    protected Person() {
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
}