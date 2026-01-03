package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "allergie")
public class AllergieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierMedicalEntity dossierMedical;

    private String substance;
    private String type;
    private String gravite;
    private String reaction;

    public AllergieEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getSubstance() { return substance; }
    public void setSubstance(String substance) { this.substance = substance; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getGravite() { return gravite; }
    public void setGravite(String gravite) { this.gravite = gravite; }

    public String getReaction() { return reaction; }
    public void setReaction(String reaction) { this.reaction = reaction; }
}
