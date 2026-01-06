package com.example.cabinetmedical.domain.model.cabinetEtat;


public class CabinetEtat {
    private boolean etat;

    public CabinetEtat(boolean etat) {
        this.etat = etat;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void activer() {
        this.etat = true;
    }

    public void desactiver() {
        this.etat = false;
    }
}
