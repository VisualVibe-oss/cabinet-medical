package com.example.cabinetmedical.application.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FactureDTO {
    private int idFacture;
    private String type;
    private Float prix;
    private String date; // Format String (ex: yyyy-MM-dd) pour le JSON
    private String state; // Nom de l'enum (PAID, PENDING, etc.)
    private Integer cabinetId; // On ne passe souvent que l'ID du cabinet pour simplifier
}