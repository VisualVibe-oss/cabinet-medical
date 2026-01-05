package com.example.cabinetmedical.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class AntecedentMedicalDTO {
    private int id;
    private String type;
    private String description;
    private Date dateDebut;

    // getters / setters
}
