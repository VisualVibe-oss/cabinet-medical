package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="depence")
public class DepenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepence;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float prix;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int periodeJour;

    @ManyToOne
    @JoinColumn(name="idCabinet")
    private CabinetEntity cabinet;

    public DepenceEntity(int idDepence, String description, Float prix, String type, Date date, int periodeJour, CabinetEntity cabinet) {
        this.idDepence = idDepence;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.date = date;
        this.periodeJour = periodeJour;
        this.cabinet = cabinet;
    }

    public DepenceEntity() {

    }

    public int getIdDepence() {
        return idDepence;
    }

    public void setIdDepence(int idDepence) {
        this.idDepence = idDepence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriodeJour() {
        return periodeJour;
    }

    public void setPeriodeJour(int periodeJour) {
        this.periodeJour = periodeJour;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }
}
