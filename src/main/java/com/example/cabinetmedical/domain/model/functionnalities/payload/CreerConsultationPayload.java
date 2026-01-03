package com.example.cabinetmedical.domain.model.functionnalities.payload;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.consultation.Consultation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreerConsultationPayload {
    Consultation consultation;  
    RendezVous  rendezVous ;
    Cabinet cabinet ;
}
