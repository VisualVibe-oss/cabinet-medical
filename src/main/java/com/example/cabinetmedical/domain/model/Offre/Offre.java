package com.example.cabinetmedical.domain.model.Offre;

import java.util.ArrayList;
import java.util.List;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.OfferType;

//! A verifier le nom si il doit etre offre ou abonement
//* cette classe cet la version domaine du class OffreEntity qui existe en base de données */
//! A verifier les attributs de la classe 
public class Offre {
    private List<Featurekey> featurekeys = new ArrayList<>();
    private OfferType type;

    // Constructeur sans-argument
    public Offre() {
        this.featurekeys = new ArrayList<>();
    }

    // Constructeur avec liste de Featurekey
    public Offre(List<Featurekey> featurekeys) {
        this.featurekeys = featurekeys == null ? new ArrayList<>() : featurekeys;
    }

    // Getter
    public List<Featurekey> getFeaturekeys() {
        return featurekeys;
    }

    // Setter
    public void setFeaturekeys(List<Featurekey> featurekeys) {
        this.featurekeys = featurekeys == null ? new ArrayList<>() : featurekeys;
    }

    public OfferType getType() {return this.type;}
}
