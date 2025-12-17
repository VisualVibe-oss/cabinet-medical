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
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;

import jakarta.persistence.*;

import java.util.List;

public class Cabinet {
    private int idCabinet;
    private String logo;
    private String nom;
    private String specialite;
    private String adresse;
    private String telephone;

    private Medecin medecin;
    private List<Secretaire> secretaire;
    private List<Depence> depence; //NEEDS TO BE ADDED
    private List<Facture> facture; //NEEDS TO BE ADDED
    private List<RendezVous> rendezVous;
    private List<Patient> patient;
    private Offre offre;
    private List<Action> action; //?
      BehaviorPack behaviorPack ;

    public Cabinet(int idCabinet, String logo, String nom, String specialite, String adresse, String telephone, Medecin medecin, List<Secretaire> secretaire, List<Depence> depence, List<Facture> facture, List<RendezVous> rendezVous, List<Patient> patient, Offre offre, List<Action> action, BehaviorPack behaviorPack) {
        this.idCabinet = idCabinet;
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.telephone = telephone;
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

    public Cabinet(){}

    public int getIdCabinet() {
        return idCabinet;
    }

    public void setIdCabinet(int idCabinet) {
        this.idCabinet = idCabinet;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Secretaire> getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(List<Secretaire> secretaire) {
        this.secretaire = secretaire;
    }

    public List<Depence> getDepence() {
        return depence;
    }

    public void setDepence(List<Depence> depence) {
        this.depence = depence;
    }

    public List<Facture> getFacture() {
        return facture;
    }

    public void setFacture(List<Facture> facture) {
        this.facture = facture;
    }

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public List<Action> getAction() {
        return action;
    }

    public void setAction(List<Action> action) {
        this.action = action;
    }

    public void setBehaviorPack(BehaviorPack behaviorPack) {
        this.behaviorPack = behaviorPack;
    }

    public BehaviorPack getBehaviorPack() {
        return behaviorPack;
    }

    public FeatureResponce performWork(FeatureParameter parameter) {
         return behaviorPack.performWork(parameter) ;
    }

}
