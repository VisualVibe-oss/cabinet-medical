package com.example.cabinetmedical.application.DTO.Stats;

import lombok.Data;

@Data
public class ExpenseBreakdownDTO {
    private String description;
    private float amount;
    private String type;
}
