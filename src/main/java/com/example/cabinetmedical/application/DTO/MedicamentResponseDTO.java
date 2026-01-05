package com.example.cabinetmedical.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MedicamentResponseDTO {

    private String nom;
    private String dosage;
    private String duration;

    // getters & setters
}