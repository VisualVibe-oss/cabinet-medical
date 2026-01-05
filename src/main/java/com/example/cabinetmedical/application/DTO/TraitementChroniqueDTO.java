package com.example.cabinetmedical.application.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class TraitementChroniqueDTO {
    private int id;
    private String medicament;
    private String dosage;
    private String frequence;
    private Date dateDebut;

    // getters / setters
}
