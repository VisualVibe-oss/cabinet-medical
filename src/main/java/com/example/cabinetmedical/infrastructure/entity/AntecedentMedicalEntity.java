package com.example.cabinetmedical.infrastructure.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "antecedent_medical")
public class AntecedentMedicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedicalEntity dossierMedical;

    private String type;
    private String description;
    private Date dateDebut;

    public AntecedentMedicalEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
}
