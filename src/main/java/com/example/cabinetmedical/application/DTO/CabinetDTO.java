package com.example.cabinetmedical.application.DTO;
// src/main/java/com/example/demo/dto/CabinetDTO.java (ou Cabinet.java)

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class CabinetDTO {

    private Integer idCabinet ; 
    private String pays;
    private String adresse;
    private boolean etat ;
    private String logo ; 
    private String nom; // Le nom du cabinet, différent du nom du médecin
    private String specialite;
    private String signatureBase64; // Optionnel côté TypeScript, mais présent côté Java

}
