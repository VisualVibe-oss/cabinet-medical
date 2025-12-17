package com.example.cabinetmedical.application.dto;
// src/main/java/com/example/demo/dto/CabinetDTO.java (ou Cabinet.java)


public class CabinetDTO {
    private String pays;
    private String adresse;
    private String nom; // Le nom du cabinet, différent du nom du médecin
    private String specialite;
    private String signatureBase64; // Optionnel côté TypeScript, mais présent côté Java

    // Getters et setters requis
    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }
    
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getSignatureBase64() { return signatureBase64; }
    public void setSignatureBase64(String signatureBase64) { this.signatureBase64 = signatureBase64; }
}
