package com.example.cabinetmedical.domain.model.Offre;

import java.util.ArrayList;
import java.util.List;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.domain.utils.OfferType;

//! A verifier le nom si il doit etre offre ou abonement
//* cette classe cet la version domaine du class OffreEntity qui existe en base de données */
//! A verifier les attributs de la classe 
public class Offre {
    private List<Featurekey> featurekeys = new ArrayList<>();
    private OfferType type;

    // Constructeur sans-argument
    public Offre(PackKey packKey) {
       this.packKey = packKey ;
    }



    // Getter
    public PackKey getPackkey() {
        return packKey;
    }

    // Setter
    public void setPackkey(PackKey packKey) {
        this.packKey = packKey ;
    }
}
