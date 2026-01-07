package com.example.cabinetmedical.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HabitudeVieDTO {
    private String tabac;
    private String alcool;
    private String alimentation;
    private String activitePhysique;
    private String sommeil;

    // getters / setters
}
