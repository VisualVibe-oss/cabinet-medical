package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.cabinetmedical.domain.utils.RendezVousState;



@Entity
@Table(name = "rendezVous")
public class RendezVousEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRendezVous;

    @Column(nullable = false)
    private LocalDateTime dateDebutRendezVous;
    @Column(nullable = false)
    private LocalDateTime dateFinRendezVous;

    @Column(nullable = false)
    private String motif;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String statut;

    @Column()
    private String notes;

    @ManyToOne
    @JoinColumn(name="idCabinet")
    private CabinetEntity cabinet;

    @ManyToOne
    @JoinColumn(name="idPatient")
    private PatientEntity patient;

    @OneToOne(mappedBy = "rendezVous")
    private ConsultationEntity consultation;

    public RendezVousEntity(int idRendezVous, Date dateRendezVous, String  consultationType ,String motif, String statut, String notes, CabinetEntity cabinet, PatientEntity patient, ConsultationEntity consultation) {
        this.idRendezVous = idRendezVous;
        this.dateDebutRendezVous = dateDebutRendezVous;
        this.dateFinRendezVous = dateFinRendezVous;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
        this.consultationType = consultationType ; 
        this.cabinet = cabinet;
        this.patient = patient;
        this.consultation = consultation;
    }
    public RendezVousEntity(){}

    public int getIdRendezVous() {
        return idRendezVous;
    }


    public String getConsultationType(){
        return consultationType  ; 
    }

    public void setConsultationType(String consultationType){
        this.consultationType = consultationType ; 
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }


    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public RendezVousState  getStatut() {
        return statut;
    }

    public void setStatut(RendezVousState statut) {
        this.statut = statut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }

    public LocalDateTime getDateDebutRendezVous() {
        return dateDebutRendezVous;
    }

    public void setDateDebutRendezVous(LocalDateTime dateDebutRendezVous) {
        this.dateDebutRendezVous = dateDebutRendezVous;
    }

    public LocalDateTime getDateFinRendezVous() {
        return dateFinRendezVous;
    }

    public void setDateFinRendezVous(LocalDateTime dateFinRendezVous) {
        this.dateFinRendezVous = dateFinRendezVous;
    }
}
