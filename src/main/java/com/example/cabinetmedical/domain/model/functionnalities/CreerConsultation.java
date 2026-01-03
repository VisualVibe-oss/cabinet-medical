package com.example.cabinetmedical.domain.model.functionnalities;

import java.util.Date;

import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.consultation.Consultation;
import com.example.cabinetmedical.domain.model.functionnalities.payload.CreerConsultationPayload;
import com.example.cabinetmedical.domain.utils.FactureState;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;


public class CreerConsultation implements Functionnalitie<CreerConsultationPayload, Consultation> {
    
    @Override
    public FeatureResponce<Consultation> performWork(FeatureParameter<CreerConsultationPayload> param) {

        Consultation consultation =  param.getPayload().getConsultation();
        RendezVous  rendezVous  =  param.getPayload().getRendezVous();

        consultation.setDate(new Date());
        int prix = rendezVous.getPrix() ;

        Facture facture = new Facture() ;
        facture.setPrix(prix);
        facture.setDate(new Date());
        facture.setState(FactureState.PENDING);
        facture.setType(consultation.getType());
        facture.setCabinet(rendezVous.getCabinet());

        consultation.setFacture(facture) ;

        return new FeatureResponce<>(Featurekey.CREE_CONSULTATION, consultation);
    }


    public CreerConsultation() {
    }
}
