package com.example.cabinetmedical.infrastructure.entity;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.*;

@Entity
@Table(name="medecin")
public class MedecinEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idMedecin;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false , unique = true)
    private String telephone;

    @OneToOne(mappedBy = "medecin",cascade = CascadeType.ALL)
    private CabinetEntity cabinet;

    public MedecinEntity() {
    }

    public MedecinEntity(int idMedecin, String nom, String prenom, String email, String password, String signature, String telephone, CabinetEntity cabinet) {
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.cabinet = cabinet;
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

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }
}
