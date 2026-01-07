package com.example.cabinetmedical.application.dto;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.domain.utils.RendezVousState;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RendezVousDTO {
    private Integer idRendezVous;
    private LocalDateTime dateDebutRendezVous;
    private LocalDateTime dateFinRendezVous;
    private String motif;
    private RendezVousState statut;
    private String notes;
    private Integer idCabinet;
    private Patient patient;
    private String consultationType;
    private int prix;
}
