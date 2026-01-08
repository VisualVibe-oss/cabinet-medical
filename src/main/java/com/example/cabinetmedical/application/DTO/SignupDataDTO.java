package com.example.cabinetmedical.application.DTO;

public class SignupDataDTO {
    // Les noms des variables doivent correspondre aux clés JSON ("medecin", "cabinet")
    private com.example.cabinetmedical.application.DTO.MedecinDTO medecin;
    private com.example.cabinetmedical.application.DTO.CabinetDTO cabinet;

    // Getters et setters requis
    public MedecinDTO getMedecin() { return medecin; }
    public void setMedecin(MedecinDTO medecin) { this.medecin = medecin; }

    public CabinetDTO getCabinet() { return cabinet; }
    public void setCabinet(CabinetDTO cabinet) { this.cabinet = cabinet; }
}
