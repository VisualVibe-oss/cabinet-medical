package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "facture")
public class FactureEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idFacture;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Float prix;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idCabinet")
    private CabinetEntity cabinet;

    public FactureEntity(int idFacture, String type, Float prix, Date date, CabinetEntity cabinet) {
        this.idFacture = idFacture;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.cabinet = cabinet;
    }

    public int getIdFacture() {
        return idFacture;
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

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
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
