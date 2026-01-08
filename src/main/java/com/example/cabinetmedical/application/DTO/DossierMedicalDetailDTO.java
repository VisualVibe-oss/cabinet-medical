package com.example.cabinetmedical.application.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DossierMedicalDetailDTO {

    private List<AntecedentMedicalDTO> antecedentsMedicaux;
    private List<AntecedentChirurgicalDTO> antecedentsChirurgicaux;
    private List<AllergieDTO> allergies;
    private List<TraitementChroniqueDTO> traitementsChroniques;
    private HabitudeVieDTO habitudesVie;

    // getters / setters
}
