package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

import com.example.cabinetmedical.domain.utils.PackKey;

@Entity
@Table(name = "offre")
public class OffreEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idOffre;

     @Column(nullable = false)
    private PackKey key;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float prix;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "offre")
    private List<CabinetEntity> cabinet;

    @Column(nullable = false)
    private int offreDurationInDays;



    public OffreEntity( int offreDurationInDays, PackKey key, String type, float prix, String description, List<CabinetEntity> cabinet) {
        this.key = key;
        this.type = type;
        this.prix = prix;
        this.description = description;
        this.cabinet = cabinet;
        this.offreDurationInDays = offreDurationInDays;
    }
    public OffreEntity(){}

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOffreDurationInDays() {
        return offreDurationInDays;
    }

    public void setOffreDurationInDays(int offreDurationInDays) {
        this.offreDurationInDays = offreDurationInDays;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CabinetEntity> getCabinet() {
        return cabinet;
    }

    public void setCabinet(List<CabinetEntity> cabinet) {
        this.cabinet = cabinet;
    }
    public PackKey getKey() {
        return key;
    }

    public void setKey(PackKey key) {
        this.key = key;
    }
}
