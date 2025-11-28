package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ordonnanceSup")
public class OrdonnanceSupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdonnanceSup;

    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "ordonnanceSup")
    private ConsultationEntity consultation;

    public OrdonnanceSupEntity(int idOrdonnanceSup, String description, ConsultationEntity consultation) {
        this.idOrdonnanceSup = idOrdonnanceSup;
        this.description = description;
        this.consultation = consultation;
    }
    public OrdonnanceSupEntity() {}

    public int getIdOrdonnanceSup() {
        return idOrdonnanceSup;
    }

    public void setIdOrdonnanceSup(int idOrdonnanceSup) {
        this.idOrdonnanceSup = idOrdonnanceSup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }
}
