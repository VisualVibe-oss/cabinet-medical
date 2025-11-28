package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ordonnanceMed")
public class OrdonnanceMedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdonnanceMed;

    @OneToOne(mappedBy = "ordonnanceMed")
    private ConsultationEntity consultation;

    @ManyToMany
    @JoinTable(name = "ordonnance_medicament",
    joinColumns = @JoinColumn(name = "idOrdonnanceMed"),
    inverseJoinColumns = @JoinColumn(name = "idMedicament"))
    private List<MedicamentEntity> medicament;

    public OrdonnanceMedEntity(int idOrdonnanceMed, ConsultationEntity consultation, List<MedicamentEntity> medicament) {
        this.idOrdonnanceMed = idOrdonnanceMed;
        this.consultation = consultation;
        this.medicament = medicament;
    }
    public OrdonnanceMedEntity() {}

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

    public List<MedicamentEntity> getMedicament() {
        return medicament;
    }

    public void setMedicament(List<MedicamentEntity> medicament) {
        this.medicament = medicament;
    }
}
