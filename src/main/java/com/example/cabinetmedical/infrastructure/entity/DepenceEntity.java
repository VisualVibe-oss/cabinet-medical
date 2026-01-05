package com.example.cabinetmedical.infrastructure.entity;

import com.example.cabinetmedical.domain.utils.DepenceType;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepenceType type; //

    @Column(nullable = false)
    private Date date;

    @Column
    private int periodeJour;

    @Column
    private Date lastGenratedDate;

    @Column
    private Date anchorDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParent")
    private DepenceEntity parent;

    @ManyToOne
    @JoinColumn(name="idCabinet")
    private CabinetEntity cabinet;

    public DepenceEntity(int idDepence, String description, Float prix, DepenceType type, Date date, int periodeJour,Date lastGenratedDate, Date anchorDate , DepenceEntity parent, CabinetEntity cabinet) {
        this.idDepence = idDepence;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.date = date;
        this.periodeJour = periodeJour;
        this.lastGenratedDate = lastGenratedDate;
        this.anchorDate = anchorDate;
        this.parent = parent;
        this.cabinet = cabinet;
    }

    public DepenceEntity() {

    }

    public DepenceEntity getParent() {
        return parent;
    }

    public void setParent(DepenceEntity parent) {
        this.parent = parent;
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

    public DepenceType getType() {
        return type;
    }

    public void setType(DepenceType type) {
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

    public Date getLastGenratedDate() {
        return lastGenratedDate;
    }

    public void setLastGenratedDate(Date lastGenratedDate) {
        this.lastGenratedDate = lastGenratedDate;
    }

    public Date getAnchorDate() {
        return anchorDate;
    }

    public void setAnchorDate(Date anchorDate) {
        this.anchorDate = anchorDate;
    }


}
