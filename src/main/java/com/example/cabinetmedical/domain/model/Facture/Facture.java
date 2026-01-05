package com.example.cabinetmedical.domain.model.Facture;


import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;



import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.utils.FactureState;
import java.util.Date;

public class Facture {
    private int idFacture;
    private String type;
    private int prix;
    private Date date;
    private Cabinet cabinet;
    private FactureState state;

    public Facture(int idFacture, String type, int prix, Date date, Cabinet cabinet, FactureState state) {
        this.idFacture = idFacture;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.cabinet = cabinet;
        this.state = state;
    }

     public Facture() {}

    // Constructeur complet, Getters et Setters
    public int getIdFacture() { return idFacture; }
    public void setIdFacture(int idFacture) { this.idFacture = idFacture; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getPrix() { return prix; }
    public void setPrix(int prix) { this.prix = prix; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public FactureState getState() { return state; }
    public void setState(FactureState state) { this.state = state; }
    public Cabinet getCabinet() { return cabinet; }
    public void setCabinet(Cabinet cabinet) { this.cabinet = cabinet; }
}
