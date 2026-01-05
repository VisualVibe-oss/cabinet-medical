package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

import lombok.Data;

public class MedecinDTO extends UserDTO {
    private int idMedecin;
    private String nom;
    private String prenom;
    private String signature;
    private String telephone;
    private Cabinet cabinet;
    private String password;

    public MedecinDTO() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
