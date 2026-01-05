package com.example.cabinetmedical.domain.model.Facture;


import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

import java.util.Date;

public class Facture {
    private int idFacture;
    private String type;
    private Float prix;
    private Date date;
    private Cabinet cabinet;

    public Facture(int idFacture, String type, Float prix, Date date, Cabinet cabinet) {
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
}
