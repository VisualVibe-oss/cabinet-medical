package com.example.cabinetmedical.application.dto.Stats;

import com.example.cabinetmedical.domain.utils.DepenceType;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private int idDepence;
    private Date date;
    private String description;
    private float amount;
    private String kind;
    private DepenceType type;
}
