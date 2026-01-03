package com.example.cabinetmedical.domain.model.Facture;


<<<<<<< HEAD
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

=======


import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.utils.FactureState;
>>>>>>> acf2c00 (feat: Add consultation creation functionality and related DTOs)
import java.util.Date;

public class Facture {
    private int idFacture;
    private String type;
<<<<<<< HEAD
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
=======
    private int prix;
    private Date date;
    private FactureState state;
    private Cabinet cabinet; // Objet domaine Cabinet

    public Facture() {}

    // Constructeur complet, Getters et Setters
    public int getIdFacture() { return idFacture; }
    public void setIdFacture(int idFacture) { this.idFacture = idFacture; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getPrix() { return prix; }
    public void setPrix(int prix2) { this.prix = prix2; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public FactureState getState() { return state; }
    public void setState(FactureState state) { this.state = state; }
    public Cabinet getCabinet() { return cabinet; }
    public void setCabinet(Cabinet cabinet) { this.cabinet = cabinet; }
}
>>>>>>> acf2c00 (feat: Add consultation creation functionality and related DTOs)
