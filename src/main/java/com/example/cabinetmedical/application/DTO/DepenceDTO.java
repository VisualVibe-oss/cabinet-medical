package com.example.cabinetmedical.application.DTO;

import com.example.cabinetmedical.domain.utils.DepenceType;
import lombok.Data;

import java.util.Date;

@Data
public class DepenceDTO {
    private int idDepence;
    private String description;
    private float prix;
    private DepenceType type; // ONE_TIME | PERIODIC
    private Date date;
    private int periodeJour; // only for PERIODIC
    private int idCabinet;
}
