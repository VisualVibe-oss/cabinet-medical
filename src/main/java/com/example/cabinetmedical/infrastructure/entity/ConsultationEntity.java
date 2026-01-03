package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consultation")
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsultation;

    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private String motif; 

    @Column(nullable = true)
    private String symptome; // Note: Stocké en String (vous pouvez utiliser une délimitation si c'est une liste)

    @Column(nullable = true)
    private Double temperature;

    @Column(nullable = true)
    private String dateSymptome;

    @Column(nullable = true)
    private Double poid;

    @Column(nullable = true)
    private Double tension;

    @Column(nullable = true)
    private Integer frequenceCardiaque;

    @Column(nullable = true)
    private String observationClinique;




    //* A changer  */
    @Column(nullable = false)
    private Date date;

    

    @OneToOne(mappedBy = "consultation" ,cascade = CascadeType.PERSIST)
    private FactureEntity facture;

    @OneToMany(mappedBy = "consultation" , cascade = CascadeType.PERSIST)
    private List<ExamenEntity> examenEntities;

    @OneToOne
    @JoinColumn(name="idRendezVous")
    private RendezVousEntity rendezVous;

    @ManyToOne
    @JoinColumn(name="idDossierMedical")
    private DossierMedicalEntity dossierMedical;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idOrdonnanceSup" )
    private OrdonnanceSupEntity ordonnanceSup;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idOrdonnanceMed")
    private OrdonnanceMedEntity ordonnanceMed;

    // --- CONSTRUCTEURS ---

    public ConsultationEntity() {}

    // Constructeur complet (mis à jour avec les nouveaux champs)
    public ConsultationEntity(int idConsultation, String type, String motif, String symptome, Double temperature, String dateSymptome, Double poid, Double tension, Integer frequenceCardiaque, String observationClinique, Date date, String examenClinique, String examenSupplementaire, String diagnostic, String observations, Boolean payement, RendezVousEntity rendezVous, DossierMedicalEntity dossierMedical, OrdonnanceSupEntity ordonnanceSup, OrdonnanceMedEntity ordonnanceMed) {
        this.idConsultation = idConsultation;
        this.type = type;
        this.motif = motif;
        this.symptome = symptome;
        this.temperature = temperature;
        this.dateSymptome = dateSymptome;
        this.poid = poid;
        this.tension = tension;
        this.frequenceCardiaque = frequenceCardiaque;
        this.observationClinique = observationClinique;
        this.date = date;
        this.rendezVous = rendezVous;
        this.dossierMedical = dossierMedical;
        this.ordonnanceSup = ordonnanceSup;
        this.ordonnanceMed = ordonnanceMed;
    }

    // --- GETTERS ET SETTERS (Nouveaux champs) ---

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getSymptome() { return symptome; }
    public void setSymptome(String symptome) { this.symptome = symptome; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public String getDateSymptome() { return dateSymptome; }
    public void setDateSymptome(String dateSymptome) { this.dateSymptome = dateSymptome; }

    public Double getPoid() { return poid; }
    public void setPoid(Double poid) { this.poid = poid; }

    public Double getTension() { return tension; }
    public void setTension(Double tension) { this.tension = tension; }

    public Integer getFrequenceCardiaque() { return frequenceCardiaque; }
    public void setFrequenceCardiaque(Integer frequenceCardiaque) { this.frequenceCardiaque = frequenceCardiaque; }

    public String getObservationClinique() { return observationClinique; }
    public void setObservationClinique(String observationClinique) { this.observationClinique = observationClinique; }

    // --- GETTERS ET SETTERS (Anciens champs inchangés) ---

    public int getIdConsultation() { return idConsultation; }
    public void setIdConsultation(int idConsultation) { this.idConsultation = idConsultation; }

    public List<ExamenEntity> getExamenEntities() { return examenEntities; }
    public void setExamenEntities(List<ExamenEntity> examenEntities) { this.examenEntities = examenEntities; }  
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public FactureEntity getFacture() { return facture; }
    public void setFacture(FactureEntity facture) { this.facture = facture;}
    public RendezVousEntity getRendezVous() { return rendezVous; }
    public void setRendezVous(RendezVousEntity rendezVous) { this.rendezVous = rendezVous; }

    public DossierMedicalEntity getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(DossierMedicalEntity dossierMedical) { this.dossierMedical = dossierMedical; }

    public OrdonnanceSupEntity getOrdonnanceSup() { return ordonnanceSup; }
    public void setOrdonnanceSup(OrdonnanceSupEntity ordonnanceSup) { this.ordonnanceSup = ordonnanceSup; }

    public OrdonnanceMedEntity getOrdonnanceMed() { return ordonnanceMed; }
    public void setOrdonnanceMed(OrdonnanceMedEntity ordonnanceMed) { this.ordonnanceMed = ordonnanceMed; }
}