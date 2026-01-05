package com.example.cabinetmedical.application.DTO.Stats;

import lombok.Data;

import java.util.Date;
@Data
public class RecurringDTO { //this DTO only concerns INITIAL_PERIODIC type transactions
    private int idDepence;
    String Description; //rent subscription etc..
    Date date; //first time the transaction was added
    float amount; //price
    int period; //how so often will it be readded (in days)
    Date next; //the date of the next payment
}
