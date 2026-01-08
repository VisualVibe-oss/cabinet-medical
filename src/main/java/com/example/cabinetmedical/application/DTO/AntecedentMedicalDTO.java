package com.example.cabinetmedical.application.DTO;

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
