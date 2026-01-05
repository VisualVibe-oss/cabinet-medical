package com.example.cabinetmedical.domain.model.proprietaire;


import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

public abstract class Propietaire {
    private int idMedecin;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    // private String signature; 
    private String telephone;
    private Cabinet cabinet;

    
    public Propietaire(int idMedecin, String nom, String prenom, String email, String password, String telephone, Cabinet cabinet) {
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        // this.signature = signature;
        this.telephone = telephone;
        this.cabinet = cabinet;
    }
    public Propietaire() {}

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
}
