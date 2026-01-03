package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.RendezVousState;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RendezVousDTO {
    private int idRendezVous;
    private LocalDateTime dateDebutRendezVous;
    private LocalDateTime dateFinRendezVous;
    private String motif;
    private RendezVousState statut;
    private String notes;
    private int idCabinet;
    private Patient patient;
    private String consultationType;
    private int prix;
}
