package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ordonnance_sup")
public class OrdonnanceSupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdonnanceSup;

    @Column(nullable = true)
    private String reposStrict;

    @Column(nullable = true)
    private String regime;

    @Column(nullable = true)
    private Boolean arretTravail; // Utilisation de l'objet Boolean pour gérer le null

    @Column(nullable = true)
    private String arretDebut;

    @Column(nullable = true)
    private String arretFin;

    @OneToOne(mappedBy = "ordonnanceSup")
    private ConsultationEntity consultation;

    // --- CONSTRUCTEURS ---

    public OrdonnanceSupEntity() {}

    public OrdonnanceSupEntity(int idOrdonnanceSup, String reposStrict, String regime, 
                               Boolean arretTravail, String arretDebut, String arretFin, 
                               ConsultationEntity consultation) {
        this.idOrdonnanceSup = idOrdonnanceSup;
        this.reposStrict = reposStrict;
        this.regime = regime;
        this.arretTravail = arretTravail;
        this.arretDebut = arretDebut;
        this.arretFin = arretFin;
        this.consultation = consultation;
    }

    // --- GETTERS ET SETTERS ---

    public int getIdOrdonnanceSup() {
        return idOrdonnanceSup;
    }

    public void setIdOrdonnanceSup(int idOrdonnanceSup) {
        this.idOrdonnanceSup = idOrdonnanceSup;
    }

    public String getReposStrict() {
        return reposStrict;
    }

    public void setReposStrict(String reposStrict) {
        this.reposStrict = reposStrict;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Boolean getArretTravail() {
        return arretTravail;
    }

    public void setArretTravail(Boolean arretTravail) {
        this.arretTravail = arretTravail;
    }

    public String getArretDebut() {
        return arretDebut;
    }

    public void setArretDebut(String arretDebut) {
        this.arretDebut = arretDebut;
    }

    public String getArretFin() {
        return arretFin;
    }

    public void setArretFin(String arretFin) {
        this.arretFin = arretFin;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }
}