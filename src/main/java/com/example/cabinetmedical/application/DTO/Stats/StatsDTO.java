package com.example.cabinetmedical.application.DTO.Stats;

import lombok.Data;

import java.util.List;

@Data
public class StatsDTO {
    private float monthIncome;
    private float monthExpense;
    private float monthNetProfit;
    private float monthGrowthIncome;
    private float monthGrowthExpense;
    private float monthGrowthNetProfit;

    private float lastmonthIncome;
    private float lastmonthNetProfit;

    private int totalAppointments;
    private float avgAppointment;
    private float peakDay;

    private List<ChartDTO> chartList;
    private List<TransactionDTO> transactionList;
    private List<ExpenseBreakdownDTO> expenseBreakdownList;
    private List<RecurringDTO>  recurringList;
}
