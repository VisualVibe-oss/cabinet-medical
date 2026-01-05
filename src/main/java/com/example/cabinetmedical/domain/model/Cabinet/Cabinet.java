package com.example.cabinetmedical.domain.model.Cabinet;

import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

public class Cabinet {
      BehaviorPack behaviorPack ;

    private int idCabinet;
    private String logo;
    private String nom;
    private String specialite;
    private String adresse;
    private String telephone;
    private boolean etat;
    public Cabinet() {
    }

    public Cabinet(int idCabinet, String logo, String nom, String specialite,
                   String adresse, String telephone, boolean etat) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this. adresse = adresse;
        this.telephone = telephone;
        this.etat = etat;
    }


    FeatureResponce performWork(FeatureParameter parameter) {
         return behaviorPack.performWork(parameter) ;

    }

    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
