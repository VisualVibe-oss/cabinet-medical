package com.example.cabinetmedical.application.DTO.cabinetStats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinetStatsDTO {
    private Integer cabinetTotale;
    private Integer cabinetBasic;
    private Integer cabinetPro;
}
