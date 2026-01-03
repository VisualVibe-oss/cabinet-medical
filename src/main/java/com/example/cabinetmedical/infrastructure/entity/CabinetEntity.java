package com.example.cabinetmedical.infrastructure.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cabinet")
public class CabinetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCabinet;

    private String logo;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String specialite;

    private String adresse;

    private String telephone;

    private boolean etat=true;

    @OneToOne()
    @JoinColumn(name="idMedecin")
    private MedecinEntity medecin;

    @OneToMany(mappedBy = "cabinet")
    private List<SecretaireEntity> secretaire;

    @OneToMany(mappedBy = "cabinet")
    private List<DepenceEntity> depence;

    @OneToMany(mappedBy = "cabinet")
    private List<FactureEntity> facture;

    @OneToMany(mappedBy = "cabinet")
    private List<RendezVousEntity> rendezVous;

    @OneToMany(mappedBy = "cabinet")
    private List<PatientEntity> patient;

    @ManyToOne
    @JoinColumn(name = "idOffre")
    private OffreEntity offre;

    @OneToMany(mappedBy = "cabinet")
    private List<ActionEntity> action;


    public CabinetEntity(int idCabinet, String logo, String nom, String specialite, String adresse, String telephone) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public CabinetEntity() {
    }

    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public MedecinEntity getMedecin() {
        return medecin;
    }

    public boolean getEtat(){ return etat; }
    public void setEtat(boolean etat) { this.etat = etat; }

    public void setMedecin(MedecinEntity medecin) {
        this.medecin = medecin;
    }

    public List<SecretaireEntity> getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(List<SecretaireEntity> secretaire) {
        this.secretaire = secretaire;
    }

    public List<DepenceEntity> getDepence() {
        return depence;
    }

    public void setDepence(List<DepenceEntity> depence) {
        this.depence = depence;
    }

    public List<FactureEntity> getFacture() {
        return facture;
    }

    public void setFacture(List<FactureEntity> facture) {
        this.facture = facture;
    }

    public List<RendezVousEntity> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVousEntity> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public List<PatientEntity> getPatient() {
        return patient;
    }

    public void setPatient(List<PatientEntity> patient) {
        this.patient = patient;
    }

    public OffreEntity getOffre() {
        return offre;
    }

    public void setOffre(OffreEntity offre) {
        this.offre = offre;
    }

    public List<ActionEntity> getAction() {
        return action;
    }

    public void setAction(List<ActionEntity> action) {
        this.action = action;
    }
}
