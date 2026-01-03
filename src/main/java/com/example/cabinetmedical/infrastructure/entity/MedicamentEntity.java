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

    // Correction : On utilise le type de l'entité de liaison (table de jointure)
    @OneToMany(mappedBy = "medicament") 
    private List<MedciamentOrdonnanceEntity> medciamentOrdonnanceEntities;

    // --- CONSTRUCTEURS ---

    public MedicamentEntity() {}

    public MedicamentEntity(int idMedicament, String nom, List<MedciamentOrdonnanceEntity> medciamentOrdonnanceEntities) {
        this.idMedicament = idMedicament;
        this.nom = nom;
        this.medciamentOrdonnanceEntities = medciamentOrdonnanceEntities;
    }

    // --- GETTERS ET SETTERS ---

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

    public List<MedciamentOrdonnanceEntity> getMedciamentOrdonnanceEntities() {
        return medciamentOrdonnanceEntities;
    }

    public void setMedciamentOrdonnanceEntities(List<MedciamentOrdonnanceEntity> medciamentOrdonnanceEntities) {
        this.medciamentOrdonnanceEntities = medciamentOrdonnanceEntities;
    }
}