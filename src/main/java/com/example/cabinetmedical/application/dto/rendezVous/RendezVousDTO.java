package com.example.cabinetmedical.application.dto.rendezVous;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    private Integer idRendezVous;
    private Date dateRendezVous;
    private String motif;
    private String statut;
    private String notes;
    private Integer idCabinet;
    private Integer idPatient;
    private Integer idSecretaire;

    private String nomPatient;
    private String prenomPatient;
    private String telephonePatient;
    private String cinPatient;

//    a voir si je les ajoutes ou pas
//    private Date dateDebut;
//    private Date dateFin;
}
