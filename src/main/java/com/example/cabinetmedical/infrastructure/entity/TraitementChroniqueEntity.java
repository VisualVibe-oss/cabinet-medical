package com.example.cabinetmedical.infrastructure.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "traitement_chronique")
public class TraitementChroniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedicalEntity dossierMedical;

    private String medicament;
    private String dosage;
    private String frequence;
    private Date dateDebut;

    public TraitementChroniqueEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getMedicament() { return medicament; }
    public void setMedicament(String medicament) { this.medicament = medicament; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getFrequence() { return frequence; }
    public void setFrequence(String frequence) { this.frequence = frequence; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
}
