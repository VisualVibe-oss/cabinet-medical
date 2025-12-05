package com.example.cabinetmedical.infrastructure.entity;


import jakarta.persistence.*;

import java.sql.Date;
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

     @Temporal(TemporalType.TIMESTAMP)
     private Date dateFinOffre;


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


    public CabinetEntity(int idCabinet, String logo,Date dateFinOffre,String nom, String specialite, String adresse, String telephone) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.dateFinOffre = dateFinOffre;
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
    public Date getDateFinOffre() {
        return dateFinOffre;
    }       
    public void setDateFinOffre(Date dateFinOffre) {
        this.dateFinOffre = dateFinOffre;
    }
}
