package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordonnance_med")
public class OrdonnanceMedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdonnanceMed;

    @OneToOne(mappedBy = "ordonnanceMed")
    private ConsultationEntity consultation;

    // Correction : Nom du champ plus explicite et type cohérent avec @OneToMany
    @OneToMany(mappedBy = "ordonnanceMed"  , cascade = CascadeType.PERSIST ) // Assurez-vous que le champ s'appelle ainsi dans MedciamentOrdonnanceEntity
    private List<MedciamentOrdonnanceEntity> medicaments;

    // --- CONSTRUCTEURS ---

    public OrdonnanceMedEntity() {}

    public OrdonnanceMedEntity(int idOrdonnanceMed, ConsultationEntity consultation, List<MedciamentOrdonnanceEntity> medicaments) {
        this.idOrdonnanceMed = idOrdonnanceMed;
        this.consultation = consultation;
        this.medicaments = medicaments;
    }

    // --- GETTERS ET SETTERS ---

    public int getIdOrdonnanceMed() {
        return idOrdonnanceMed;
    }

    public void setIdOrdonnanceMed(int idOrdonnanceMed) {
        this.idOrdonnanceMed = idOrdonnanceMed;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }

    public List<MedciamentOrdonnanceEntity> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<MedciamentOrdonnanceEntity> medicaments) {
        this.medicaments = medicaments;
    }
}