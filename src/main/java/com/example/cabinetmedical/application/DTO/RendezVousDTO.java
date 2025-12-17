package com.example.cabinetmedical.application.dto;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.patient.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class RendezVousDTO {
    private int idRendezVous;
    private Date dateRendezVous;
    private String motif;
    private String statut;
    private String notes;
    private Cabinet cabinet;
    private Patient patient;

}
