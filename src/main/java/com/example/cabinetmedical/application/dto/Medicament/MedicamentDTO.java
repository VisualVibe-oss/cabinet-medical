package com.example.cabinetmedical.application.dto.Medicament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentDTO {
    private int id;
    private String nom;
}
