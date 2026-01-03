package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "antecedent_chirurgical")
public class AntecedentChirurgicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedicalEntity dossierMedical;

    private String intervention;
    private Integer annee;
    private String complications;

    public AntecedentChirurgicalEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getIntervention() { return intervention; }
    public void setIntervention(String intervention) { this.intervention = intervention; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public String getComplications() { return complications; }
    public void setComplications(String complications) { this.complications = complications; }
}
