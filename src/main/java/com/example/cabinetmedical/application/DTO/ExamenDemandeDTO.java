package com.example.cabinetmedical.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public
class ExamenDemandeDTO {
    private String type;
    private String etat; 
    private String base64File  ; 
}
