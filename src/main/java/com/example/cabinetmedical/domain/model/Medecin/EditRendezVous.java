package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.utils.payload.EditRendezVousPayload;
import com.example.cabinetmedical.exception.OverlappedAppointmentException;

import java.time.LocalDateTime;
import java.util.List;

public class EditRendezVous implements Functionnalitie {

    @Override
    public FeatureResponce<RendezVous> performWork(FeatureParameter param) {
        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }
        if (!(param.getPayload() instanceof EditRendezVousPayload)){
            throw new IllegalArgumentException("the payload is not of type EditRendezVousPayload");
        }
        EditRendezVousPayload payload =
                (EditRendezVousPayload) param.getPayload();
        RendezVous current = payload.getCurrent();
        List<RendezVous> existingrvs = payload.getExistingrvs();

        LocalDateTime newStart = payload.getDateDebutRendezVous() != null
                ? payload.getDateDebutRendezVous()
                : current.getDateDebutRendezVous();

        LocalDateTime newEnd = payload.getDateFinRendezVous() != null
                ? payload.getDateFinRendezVous()
                : current.getDateFinRendezVous();

        for (RendezVous rv: existingrvs) {
            if(rv.getIdRendezVous() == current.getIdRendezVous() ) continue;

            if(newStart.isBefore(rv.getDateFinRendezVous()) && newEnd.isAfter(rv.getDateDebutRendezVous())) {
                throw new OverlappedAppointmentException("the new date overlaps with an existing one");
            }
        }



        if (payload.getDateDebutRendezVous() != null) {
            current.setDateDebutRendezVous(payload.getDateDebutRendezVous());
        }

        if (payload.getDateFinRendezVous() != null) {
            current.setDateFinRendezVous(payload.getDateFinRendezVous());
        }

        if (payload.getMotif() != null) {
            current.setMotif(payload.getMotif());
        }

        if (payload.getStatut() != null) {
            current.setStatut(payload.getStatut());
        }

        if (payload.getNotes() != null) {
            current.setNotes(payload.getNotes());
        }

        if (payload.getPatient() != null) {
            current.setPatient(payload.getPatient());
        }

        if (payload.getCabinet() != null) {
            current.setCabinet(payload.getCabinet());
        }
        return new FeatureResponce<>(
                Featurekey.EDIT_RENDEZ_VOUS,
                current
        );
    }
}
