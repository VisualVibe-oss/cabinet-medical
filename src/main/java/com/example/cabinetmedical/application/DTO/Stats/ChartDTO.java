package com.example.cabinetmedical.application.DTO.Stats;

import lombok.Data;

@Data
public class ChartDTO {
    private String month; //should be month-year
    private float amount;
    private String type; //expense or income

    public ChartDTO(String month, float amount, String type) {
        this.month = month;
        this.amount = amount;
        this.type = type;
    }
}
