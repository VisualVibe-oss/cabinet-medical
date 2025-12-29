package com.example.cabinetmedical.domain.model.Offre;

import java.util.ArrayList;
import java.util.List;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.PackKey;

import lombok.Data;

import com.example.cabinetmedical.domain.utils.OfferType;

//! A verifier le nom si il doit etre offre ou abonement
//* cette classe cet la version domaine du class OffreEntity qui existe en base de données */
//! A verifier les attributs de la classe 

public class Offre {
    private OfferType type;
    private PackKey packKey ; 

    // Constructeur sans-argument
    public Offre(PackKey packKey) {
       this.packKey = packKey ;
    }
    public Offre() {

    }

    public OfferType getType(){return type;}
    public void setType(OfferType type){this.type = type ; }
    // Getter
    public PackKey getPackkey() {
        return packKey;
    }

    // Setter
    public void setPackkey(PackKey packKey) {
        this.packKey = packKey ;
    }

    public List<Featurekey> getFeaturekeys() {
        return featurekeys;
    }

    public void setFeaturekeys(List<Featurekey> featurekeys) {
        this.featurekeys = featurekeys;
    }

    public OfferType getType() {
        return type;
    }

    public void setType(OfferType type) {
        this.type = type;
    }

}
