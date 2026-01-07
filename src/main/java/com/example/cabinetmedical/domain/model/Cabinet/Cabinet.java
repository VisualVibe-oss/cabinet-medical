package com.example.cabinetmedical.domain.model.Cabinet;

import com.example.cabinetmedical.domain.model.Action.Action;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.domain.model.Offre.Offre;
import com.example.cabinetmedical.domain.model.behaviorPack.BehaviorPack;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;

import lombok.Data;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;


import java.util.List;



@Data
public class Cabinet {
    private int idCabinet;
    private String logo;
    private String nom;
    private String specialite;
    private String adresse;

    private String signatureBase64 ;
    

    private boolean etat;

    private Medecin medecin;
    private List<Secretaire> secretaire;
    private List<Depence> depence; //NEEDS TO BE ADDED
    private List<Facture> facture; //NEEDS TO BE ADDED
    private List<RendezVous> rendezVous;
    private List<Patient> patient;
    private Offre offre;
    private List<Action> action; //?

    BehaviorPack behaviorPack ;

    public Cabinet(int idCabinet, boolean etat ,String 
        logo, String nom, String specialite, String adresse, 
        Medecin medecin, List<Secretaire> secretaire, List<Depence> depence,
         List<Facture> facture, List<RendezVous> rendezVous, List<Patient> patient,
          Offre offre, List<Action> action, BehaviorPack behaviorPack) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.etat = etat ;
        this.medecin = medecin;
        this.secretaire = secretaire;
        this.depence = depence;
        this.facture = facture;
        this.rendezVous = rendezVous;
        this.patient = patient;
        this.offre = offre;
        this.action = action;
        this.behaviorPack = behaviorPack;
    }
    public Cabinet (){}

    public Cabinet(int idCabinet, String logo, String nom, String specialite,
                   String adresse, String telephone, boolean etat) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this. adresse = adresse;
        this.etat = etat;
    }


    public String getSignatureBase64(){
        return this.signatureBase64 ; 
    }

    public void setSignatureBase64 (String sig){
        this.signatureBase64 = sig ; 
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
