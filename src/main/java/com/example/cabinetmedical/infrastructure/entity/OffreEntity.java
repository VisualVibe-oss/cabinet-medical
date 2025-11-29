package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "offre")
public class OffreEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idOffre;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float prix;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "offre")
    private List<CabinetEntity> cabinet;

    @ManyToMany
    @JoinTable(name = "offre_functionalite",
    joinColumns =@JoinColumn(name = "idOffre"),
    inverseJoinColumns = @JoinColumn(name = "IdFunctionalite"))
    private List<FunctionaliteEntity> functionalite;

    public OffreEntity(int idOffre, String type, float prix, String description, List<CabinetEntity> cabinet, List<FunctionaliteEntity> functionalite) {
        this.idOffre = idOffre;
        this.type = type;
        this.prix = prix;
        this.description = description;
        this.cabinet = cabinet;
        this.functionalite = functionalite;
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

    public List<FunctionaliteEntity> getFunctionalite() {
        return functionalite;
    }

    public void setFunctionalite(List<FunctionaliteEntity> functionalite) {
        this.functionalite = functionalite;
    }
}
