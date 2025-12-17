package com.example.cabinetmedical.domain.model.Employee;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

public abstract class Employee {
    private int idSecretaire;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Float salaire;
    private String telephone;
    private Cabinet cabinet;

    public Employee(int idSecretaire, String nom, String prenom, String email, String password, Float salaire, String telephone, Cabinet cabinet) {
        this.idSecretaire = idSecretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.salaire = salaire;
        this.telephone = telephone;
        this.cabinet = cabinet;
    }

    protected Employee() {
    }

    public int getIdSecretaire() {
        return idSecretaire;
    }

    public void setIdSecretaire(int idSecretaire) {
        this.idSecretaire = idSecretaire;
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

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
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