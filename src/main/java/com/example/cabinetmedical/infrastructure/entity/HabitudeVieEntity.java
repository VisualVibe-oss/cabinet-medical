package com.example.cabinetmedical.infrastructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitude_vie")
public class HabitudeVieEntity {

    @Id
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "dossier_id")
    private DossierMedicalEntity dossierMedical;

    private String tabac;
    private String alcool;
    private String alimentation;
    private String activitePhysique;
    private String sommeil;

    public HabitudeVieEntity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public String getTabac() { return tabac; }
    public void setTabac(String tabac) { this.tabac = tabac; }

    public String getAlcool() { return alcool; }
    public void setAlcool(String alcool) { this.alcool = alcool; }

    public String getAlimentation() { return alimentation; }
    public void setAlimentation(String alimentation) { this.alimentation = alimentation; }

    public String getActivitePhysique() { return activitePhysique; }
    public void setActivitePhysique(String activitePhysique) { this.activitePhysique = activitePhysique; }

    public String getSommeil() { return sommeil; }
    public void setSommeil(String sommeil) { this.sommeil = sommeil; }
}
