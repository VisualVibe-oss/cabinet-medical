package com.example.cabinetmedical.infrastructure.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "functionalite")
public class FunctionaliteEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int IdFunctionalite;

    @Column(nullable = false)
    private String key;

    @ManyToMany(mappedBy = "functionalite")
    private List<OffreEntity> offre;

    public FunctionaliteEntity(int idFunctionalite, String key, List<OffreEntity> offre) {
        IdFunctionalite = idFunctionalite;
        this.key = key;
        this.offre = offre;
    }

    public FunctionaliteEntity() {
    }

    public int getIdFunctionalite() {
        return IdFunctionalite;
    }

    public void setIdFunctionalite(int idFunctionalite) {
        IdFunctionalite = idFunctionalite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<OffreEntity> getOffre() {
        return offre;
    }

    public void setOffre(List<OffreEntity> offre) {
        this.offre = offre;
    }
}
