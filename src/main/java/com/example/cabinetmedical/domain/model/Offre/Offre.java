package com.example.cabinetmedical.domain.model.Offre;

import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.domain.utils.OfferType;

public class Offre {
    private PackKey packKey;
    private OfferType type;
    private float prix;
    private String description;
    private int offreDurationInDays;

    public Offre() {}

    public Offre(PackKey packKey) {
        this.packKey = packKey;
    }

    // Getters et Setters
    public PackKey getPackKey() { return packKey; }
    public void setPackKey(PackKey packKey) { this.packKey = packKey; }

    public OfferType getType() { return type; }
    public void setType(OfferType type) { this.type = type; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getOffreDurationInDays() { return offreDurationInDays; }
    public void setOffreDurationInDays(int offreDurationInDays) { this.offreDurationInDays = offreDurationInDays; }
}