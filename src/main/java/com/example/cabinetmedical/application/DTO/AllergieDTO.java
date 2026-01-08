package com.example.cabinetmedical.application.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AllergieDTO {
    private int id;
    private String substance;
    private String type;
    private String gravite;
    private String reaction;

    // getters / setters
}
