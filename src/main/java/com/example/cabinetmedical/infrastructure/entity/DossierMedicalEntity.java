package com.example.cabinetmedical.infrastructure.entity;




import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dossier_medical")
public class DossierMedicalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDossier;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCreation;

    @Column(nullable = false)
    private String statut; // ACTIF / ARCHIVE

    @OneToOne(mappedBy = "dossierMedical")
    private PatientEntity patient;

    @OneToMany(mappedBy = "dossierMedical")
    private List<ConsultationEntity> consultations;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<AntecedentMedicalEntity> antecedentsMedicaux;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<AntecedentChirurgicalEntity> antecedentsChirurgicaux;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<AllergieEntity> allergies;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private List<TraitementChroniqueEntity> traitementsChroniques;

    @OneToOne(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    private HabitudeVieEntity habitudesVie;

    // @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL)
    // private List<DocumentMedicalEntity> documentsMedicaux;

    

    public DossierMedicalEntity() {}

    public Integer getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(Integer idDossier) {
        this.idDossier = idDossier;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public List<ConsultationEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationEntity> consultations) {
        this.consultations = consultations;
    }

    public List<AntecedentMedicalEntity> getAntecedentsMedicaux() {
        return antecedentsMedicaux;
    }

    public void setAntecedentsMedicaux(List<AntecedentMedicalEntity> antecedentsMedicaux) {
        this.antecedentsMedicaux = antecedentsMedicaux;
    }

    public List<AntecedentChirurgicalEntity> getAntecedentsChirurgicaux() {
        return antecedentsChirurgicaux;
    }

    public void setAntecedentsChirurgicaux(List<AntecedentChirurgicalEntity> antecedentsChirurgicaux) {
        this.antecedentsChirurgicaux = antecedentsChirurgicaux;
    }

    public List<AllergieEntity> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<AllergieEntity> allergies) {
        this.allergies = allergies;
    }

    public List<TraitementChroniqueEntity> getTraitementsChroniques() {
        return traitementsChroniques;
    }

    public void setTraitementsChroniques(List<TraitementChroniqueEntity> traitementsChroniques) {
        this.traitementsChroniques = traitementsChroniques;
    }

    public HabitudeVieEntity getHabitudesVie() {
        return habitudesVie;
    }

    public void setHabitudesVie(HabitudeVieEntity habitudesVie) {
        this.habitudesVie = habitudesVie;
    }


    
}
