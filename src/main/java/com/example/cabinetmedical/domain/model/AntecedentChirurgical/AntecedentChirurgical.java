package com.example.cabinetmedical.domain.model.AntecedentChirurgical;

public class AntecedentChirurgical {
    private int id;
    private int dossierId;
    private String intervention;
    private Integer annee;
    private String complications;

    public AntecedentChirurgical() {
    }

    public AntecedentChirurgical(int id, int dossierId, String intervention, Integer annee, String complications) {
        this.id = id;
        this.dossierId = dossierId;
        this.intervention = intervention;
        this.annee = annee;
        this.complications = complications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public String getIntervention() {
        return intervention;
    }

    public void setIntervention(String intervention) {
        this.intervention = intervention;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getComplications() {
        return complications;
    }

    public void setComplications(String complications) {
        this.complications = complications;
    }
}
