package com.example.cabinetmedical.domain.model.Allergie;

public class Allergie {
    private int id;
    private int dossierId;
    private String substance;
    private String type;
    private String gravite;
    private String reaction;

    public Allergie() {
    }

    public Allergie(int id, int dossierId, String substance, String type, String gravite, String reaction) {
        this.id = id;
        this.dossierId = dossierId;
        this.substance = substance;
        this.type = type;
        this.gravite = gravite;
        this.reaction = reaction;
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

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}
