package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

import com.example.cabinetmedical.domain.utils.FactureState;

@Entity
@Table(name = "facture")
public class FactureEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idFacture;

    @Column(nullable = true)
    private String type;

    @Column(nullable = false)
    private int prix;

    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "id_consultation")
    private ConsultationEntity consultation;
    

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FactureState state ;

    @ManyToOne
    @JoinColumn(name = "idCabinet")
    private CabinetEntity cabinet;

    public FactureEntity(int idFacture, String type, int prix, Date date, CabinetEntity cabinet) {
        this.idFacture = idFacture;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.cabinet = cabinet;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }   
    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }



    public int getIdFacture() {
        return idFacture;
    }

    public void setState(FactureState state) {
        this.state = state;
    }   

    public FactureState getState() {
        return state;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }

    public FactureEntity() {

    }
}
