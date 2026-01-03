package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPatient;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String cin;



    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String sexe;

    @Column(nullable = false)
    private Date dateNaissance;

    @Column(nullable = false)
    private String typeMutuelle;

    @ManyToOne
    @JoinColumn(name="idCabinet")
    private CabinetEntity cabinet;

    @OneToMany(mappedBy = "patient")
    private List<RendezVousEntity> rendezVous;

    @OneToOne()
    @JoinColumn(name="idDossierMedical")
    private DossierMedicalEntity dossierMedical;

    public PatientEntity() {}

    public PatientEntity(int idPatient, String nom, String prenom, String cin,  String telephone, String sexe, Date dateNaissance, String typeMutuelle, CabinetEntity cabinet, List<RendezVousEntity> rendezVous, DossierMedicalEntity dossierMedical) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.typeMutuelle = typeMutuelle;
        this.cabinet = cabinet;
        this.rendezVous = rendezVous;
        this.dossierMedical = dossierMedical;
    }
    public PatientEntity(int idPatient, String nom, String prenom, String cin,  String telephone, String sexe, Date dateNaissance, String typeMutuelle) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.typeMutuelle = typeMutuelle;
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

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }

    public List<RendezVousEntity> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVousEntity> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public DossierMedicalEntity getDossierMedical() {
        return dossierMedical;
    }

    public void setDossierMedical(DossierMedicalEntity dossierMedical) {
        this.dossierMedical = dossierMedical;
    }
}
