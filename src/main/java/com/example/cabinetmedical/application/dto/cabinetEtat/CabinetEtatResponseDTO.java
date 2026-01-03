package com.example.cabinetmedical.application.dto.cabinetEtat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinetEtatResponseDTO {
    private Integer idCabinet;
    private String nom;
    private Boolean estActif;
}
