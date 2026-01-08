package com.example.cabinetmedical.application.DTO;

import lombok.Data;

@Data
public class FinanceDto {
    private String month;
    private float revenue;
    private float revenueGrowth;
    private float lastRevenue;
    private float expenses;
    private float netProfit;
    private float averagePayment;
    private int totalApp;
}
