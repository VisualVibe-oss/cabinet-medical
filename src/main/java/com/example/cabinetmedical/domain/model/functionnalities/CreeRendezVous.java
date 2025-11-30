package com.example.cabinetmedical.domain.model.functionnalities;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.model.rendezvous.RendezVous;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;

import java.util.Date;

public class CreeRendezVous implements Functionnalitie<RendezVous, RendezVous>  {

    @Override
    public FeatureResponce<RendezVous> performWork(FeatureParameter<RendezVous> param){
        RendezVous rendezVousPayload = (RendezVous) param.getPayload();
        rendezVousPayload.setStatut("Scheduled");

        if (rendezVousPayload.getNotes() == null){
            rendezVousPayload.setNotes("");
        }

        return new FeatureResponce<>(Featurekey.CREE_RENDEZ_VOUS, rendezVousPayload);

    }
}
