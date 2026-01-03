package com.example.cabinetmedical.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public
class MedicamentPrescritDTO {
    private String nom;
    private String dosage;
    private String duration;
}
