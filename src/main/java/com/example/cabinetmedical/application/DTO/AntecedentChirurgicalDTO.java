package com.example.cabinetmedical.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AntecedentChirurgicalDTO {
    private int id;
    private String intervention;
    private Integer annee;
    private String complications;

    // getters / setters
}
