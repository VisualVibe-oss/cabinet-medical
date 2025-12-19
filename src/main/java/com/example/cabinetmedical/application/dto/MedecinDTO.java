package com.example.cabinetmedical.application.dto;

public class MedecinDTO extends UserDTO {
    private String nom;
    private String prenom;
    private String numero;
    private String motDePasse;

    // Assurez-vous d'inclure les getters et les setters pour que Jackson (le convertisseur JSON de Spring) puisse fonctionner
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}
