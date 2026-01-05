package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MedecinDTO extends UserDTO {
    private int idMedecin;
    private String nom;
    private String prenom;
    private String signature;
    private String telephone;
    private Cabinet cabinet;
    private String password ;
   

    
}
