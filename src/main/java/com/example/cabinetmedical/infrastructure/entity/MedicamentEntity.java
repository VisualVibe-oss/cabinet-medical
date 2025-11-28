package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medicament")
public class MedicamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedicament;

    @Column(nullable = false)
    private String nom;

    @ManyToMany(mappedBy = "medicament")
    private List<OrdonnanceMedEntity> ordonnanceMed;

    public MedicamentEntity(int idMedicament, String nom, List<OrdonnanceMedEntity> ordonnanceMed) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.ordonnanceMed = ordonnanceMed;
    }

    public MedicamentEntity() {}

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<OrdonnanceMedEntity> getOrdonnanceMed() {
        return ordonnanceMed;
    }

    public void setOrdonnanceMed(List<OrdonnanceMedEntity> ordonnanceMed) {
        this.ordonnanceMed = ordonnanceMed;
    }
}
