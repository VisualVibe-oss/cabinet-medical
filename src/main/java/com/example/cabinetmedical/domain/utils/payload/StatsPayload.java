package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.Facture.Facture;

import java.util.List;

public class StatsPayload {
    List<Depence> lastMonthDepences;
    List<Facture> lastMonthFactures;
    List<Depence> allDepences;
    List<Facture> allFactures;
    List<Depence> monthDepences;
    List<Facture> monthFactures;

    public StatsPayload(List<Depence> lastMonthDepences, List<Facture> lastMonthFactures, List<Depence> allDepences, List<Facture> allFactures, List<Depence> monthDepences, List<Facture> monthFactures) {
        this.lastMonthDepences = lastMonthDepences;
        this.lastMonthFactures = lastMonthFactures;
        this.allDepences = allDepences;
        this.allFactures = allFactures;
        this.monthDepences = monthDepences;
        this.monthFactures = monthFactures;
    }

    public List<Depence> getLastMonthDepences() {
        return lastMonthDepences;
    }

    public void setLastMonthDepences(List<Depence> lastMonthDepences) {
        this.lastMonthDepences = lastMonthDepences;
    }

    public List<Facture> getLastMonthFactures() {
        return lastMonthFactures;
    }

    public void setLastMonthFactures(List<Facture> lastMonthFactures) {
        this.lastMonthFactures = lastMonthFactures;
    }

    public List<Depence> getAllDepences() {
        return allDepences;
    }

    public void setAllDepences(List<Depence> allDepences) {
        this.allDepences = allDepences;
    }

    public List<Facture> getAllFactures() {
        return allFactures;
    }

    public void setAllFactures(List<Facture> allFactures) {
        this.allFactures = allFactures;
    }

    public List<Depence> getMonthDepences() {
        return monthDepences;
    }

    public void setMonthDepences(List<Depence> monthDepences) {
        this.monthDepences = monthDepences;
    }

    public List<Facture> getMonthFactures() {
        return monthFactures;
    }

    public void setMonthFactures(List<Facture> monthFactures) {
        this.monthFactures = monthFactures;
    }
}
