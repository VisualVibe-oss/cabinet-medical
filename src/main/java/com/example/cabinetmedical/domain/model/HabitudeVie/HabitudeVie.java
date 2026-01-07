package com.example.cabinetmedical.domain.model.HabitudeVie;

public class HabitudeVie {
    private int dossierId;
    private String tabac;
    private String alcool;
    private String alimentation;
    private String activitePhysique;
    private String sommeil;

    public HabitudeVie() {
    }

    public HabitudeVie(int dossierId, String tabac, String alcool, String alimentation,
                       String activitePhysique, String sommeil) {
        this.dossierId = dossierId;
        this.tabac = tabac;
        this.alcool = alcool;
        this.alimentation = alimentation;
        this.activitePhysique = activitePhysique;
        this.sommeil = sommeil;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public String getTabac() {
        return tabac;
    }

    public void setTabac(String tabac) {
        this.tabac = tabac;
    }

    public String getAlcool() {
        return alcool;
    }

    public void setAlcool(String alcool) {
        this.alcool = alcool;
    }

    public String getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(String alimentation) {
        this.alimentation = alimentation;
    }

    public String getActivitePhysique() {
        return activitePhysique;
    }

    public void setActivitePhysique(String activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getSommeil() {
        return sommeil;
    }

    public void setSommeil(String sommeil) {
        this.sommeil = sommeil;
    }
}
