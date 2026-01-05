package com.example.cabinetmedical.domain.model.Depence;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import jakarta.persistence.*;

import java.util.Date;

public class Depence {
    private int idDepence;
    private String description;
    private Float prix;
    private DepenceType type;
    private Date date;
    private int periodeJour;
    private Date lastGeneratedDate;
    private Date anchorDate; //EXCLUSIVE to initial_periodic type transactions, it's different from date in the sense that we use it for scheduling and is NEVER changed after that, date variable refers to the date at which the transaction happened and we need it for periodic and one time type transactions (since those ones dont and shouldnt have anchor dates)
    private Cabinet cabinet;
    private Depence parent; // optional, mapped in dm.toDomain/fromEntity


    public Depence(int idDepence, String description, Float prix, DepenceType type, Date date, int periodeJour,Date lastGeneratedDate, Date anchorDate, Cabinet cabinet,  Depence parent) {
        this.idDepence = idDepence;
        this.description = description;
        this.prix = prix;
        this.type = type;
        this.date = date;
        this.periodeJour = periodeJour;
        this.lastGeneratedDate = lastGeneratedDate;
        this.anchorDate = anchorDate;
        this.cabinet = cabinet;
        this.parent = parent;
    }
    public Depence() {}

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

    public Depence getParent() {
        return parent;
    }

    public void setParent(Depence parent) {
        this.parent = parent;
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

    public Date getLastGeneratedDate() {
        return lastGeneratedDate;
    }

    public void setLastGeneratedDate(Date lastGeneratedDate) {
        this.lastGeneratedDate = lastGeneratedDate;
    }

    public Date getAnchorDate() {
        return anchorDate;
    }

    public void setAnchorDate(Date anchorDate) {
        this.anchorDate = anchorDate;
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
}
