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

    public Cabinet(int idCabinet, String logo, String nom, String specialite, String adresse, Medecin medecin, List<Secretaire> secretaire, List<Depence> depence, List<Facture> facture, List<RendezVous> rendezVous, List<Patient> patient, Offre offre, List<Action> action, BehaviorPack behaviorPack) {
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
    public Cabinet (){}

}
