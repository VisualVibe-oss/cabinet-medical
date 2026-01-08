package com.example.cabinetmedical.application.DTO.cabinetEtat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinetEtatRequestDTO {
    private boolean estActif;
}
