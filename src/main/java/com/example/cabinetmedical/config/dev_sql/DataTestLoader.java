package com.example.cabinetmedical.config.dev_sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.domain.utils.RendezVousState;
import com.example.cabinetmedical.infrastructure.entity.AllergieEntity;
import com.example.cabinetmedical.infrastructure.entity.AntecedentChirurgicalEntity;
import com.example.cabinetmedical.infrastructure.entity.AntecedentMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.ConsultationEntity;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.HabitudeVieEntity;
import com.example.cabinetmedical.infrastructure.entity.MedciamentOrdonnanceEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.MedicamentEntity;
import com.example.cabinetmedical.infrastructure.entity.OffreEntity;
import com.example.cabinetmedical.infrastructure.entity.OrdonnanceMedEntity;
import com.example.cabinetmedical.infrastructure.entity.OrdonnanceSupEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.entity.TraitementChroniqueEntity;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.ConsultationRepositroy;
import com.example.cabinetmedical.infrastructure.repository.DossierMedicalRepository;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.OffreRepository;
import com.example.cabinetmedical.infrastructure.repository.PatientRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;

;

@Component
@Profile("dev")
public class DataTestLoader {

    @Autowired
    private CabinetRepository cabinetRepo;
    @Autowired
    private MedecinRepository medecinRepo;
    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    private SecretaireRepository secretaireRepo;
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private SpringRendezVousRepository rdvRepo;
    @Autowired 
    private OffreRepository offreRepository ; 
    @Autowired 
    private PasswordEncoder passwordEncoder ;

    @Autowired 
    private ConsultationRepositroy consultationRepository    ;


    // 1. Insérer un Cabinet
    public CabinetEntity createCabinet() {
        MedecinEntity medecin = new MedecinEntity();
        medecin.setNom("Benani");
        medecin.setPrenom("Driss");
        String email =  "dr.benani@email.com" ; 
        medecin.setEmail(email.toUpperCase());
        medecin.setPassword(passwordEncoder.encode("password123"));
        medecin.setTelephone("0612345678");
        
        OffreEntity offreEntity = offreRepository.findByPackKey(PackKey.BASIC).get();

        CabinetEntity cabinet = new CabinetEntity();
        cabinet.setNom("Cabinet Al Amal");
        cabinet.setSpecialite("Cardiologie");
        cabinet.setPays("Maroc");
        cabinet.setSignatureBase64("base64string...");
        cabinet.setMedecin(medecin);
        cabinet.setOffre(offreEntity);
        return cabinetRepo.save(cabinet);
    }

    // 3. Insérer une Secrétaire
    public SecretaireEntity createSecretaire(CabinetEntity cabinet) {
        SecretaireEntity secretaire = new SecretaireEntity();
        secretaire.setNom("Fassi");
        secretaire.setPrenom("Sanaa");
        String email = "sanaa@email.com" ;
        secretaire.setEmail(email.toUpperCase());
        secretaire.setPassword(passwordEncoder.encode("password123"));
        secretaire.setSalaire(5000f);
        secretaire.setCabinet(cabinet);
        return secretaireRepo.save(secretaire);
    }

public PatientEntity createPatient(CabinetEntity cabinet, String nom) {
    PatientEntity patient = new PatientEntity();
    
    // Champs obligatoires selon vos annotations @Column(nullable = false)
    patient.setNom(nom);
    patient.setPrenom("PrénomTest");
    patient.setCin("B123456"); // Obligatoire
    patient.setPassword("password123"); // Obligatoire
    patient.setTelephone("0611223344"); // Obligatoire
    patient.setSexe("M"); // Obligatoire
    patient.setDateNaissance(new java.util.Date()); // Obligatoire
    patient.setTypeMutuelle("CNOPS"); // Obligatoire
    
    // Relation
    patient.setCabinet(cabinet);

    // Note : idPatient sera généré automatiquement (IDENTITY)
    // rendezVous et dossierMedical peuvent rester null ou vides au début
    
    return patientRepo.save(patient);
}

    // 5. Insérer des Rendez-vous
    public RendezVousEntity createRendezVous(CabinetEntity cabinet, PatientEntity patient) {
        RendezVousEntity rdv = new RendezVousEntity();
        // Obtenir la date d'aujourd'hui + 7 jours
        LocalDateTime dateCreation = LocalDateTime.now() ;


        rdv.setDateDebutRendezVous(dateCreation);
        rdv.setMotif("Consultation de routine");
        rdv.setStatut(RendezVousState.SCHEDULED);
        rdv.setNotes("Pas de notes particulières");
        rdv.setCabinet(cabinet);
        rdv.setConsultationType("Visite Hebdomadaire");
        rdv.setPatient(patient);
        return rdvRepo.save(rdv);
    }


    public RendezVousEntity createPastRendezVous(CabinetEntity cabinet, PatientEntity patient) {

     LocalDateTime dateCreation = LocalDateTime.now() ;
    RendezVousEntity rdv = new RendezVousEntity();
    rdv.setDateDebutRendezVous(dateCreation);
    rdv.setMotif("Douleurs abdominales");
    rdv.setStatut(RendezVousState.DONE);
    rdv.setConsultationType("Urgence");
    rdv.setCabinet(cabinet);
    rdv.setPatient(patient);

    return rdvRepo.save(rdv);
}

public RendezVousEntity createFutureRendezVous(CabinetEntity cabinet, PatientEntity patient) {
    LocalDateTime dateCreation = LocalDateTime.now() ;
    RendezVousEntity rdv = new RendezVousEntity();
    rdv.setDateDebutRendezVous(dateCreation);
    rdv.setMotif("Contrôle général");
    rdv.setStatut(RendezVousState.SCHEDULED);
    rdv.setConsultationType("Contrôle");
    rdv.setCabinet(cabinet);
    rdv.setPatient(patient);

    return rdvRepo.save(rdv);
}



public OrdonnanceMedEntity createOrdonnanceMed(ConsultationEntity consultation) {
    OrdonnanceMedEntity ordonnance = new OrdonnanceMedEntity();
    ordonnance.setConsultation(consultation);

    MedicamentEntity med1 = new MedicamentEntity();
    med1.setNom("Paracétamol");
    MedciamentOrdonnanceEntity m1 = new MedciamentOrdonnanceEntity();
    m1.setMedicament(med1);
    m1.setDuration("5 jours");
    m1.setDosage("500mg toutes les 6 heures");

   

    ordonnance.setMedicaments(List.of(m1));

    return ordonnance;
}



public OrdonnanceSupEntity createOrdonnanceSup(ConsultationEntity consultation) {
    OrdonnanceSupEntity sup = new OrdonnanceSupEntity();

    sup.setReposStrict("3 jours");
    sup.setRegime("Alimentation légère");
    sup.setArretTravail(true);
    sup.setArretDebut("2023-10-26");
    sup.setArretFin("2023-10-29");

    sup.setConsultation(consultation);
    return sup;
}
 



public ConsultationEntity createConsultationWithoutDossierMedical(
        RendezVousEntity rdv
    
) {
    ConsultationEntity consultation = new ConsultationEntity();

    consultation.setType("Urgence");
    consultation.setMotif("Douleurs abdominales sévères");
    consultation.setSymptome("Nausées, Fièvre légère");
    consultation.setTemperature(38.5);
    consultation.setDateSymptome("2023-10-25");
    consultation.setPoid(75.4);
    consultation.setTension(14.8);
    consultation.setFrequenceCardiaque(95);
    consultation.setObservationClinique(
        "Sensibilité fosse iliaque droite, suspicion appendicite"
    );
    consultation.setDate(new Date());

    consultation.setRendezVous(rdv);
   

    // Ordonnances
    consultation.setOrdonnanceMed(createOrdonnanceMed(consultation));
    consultation.setOrdonnanceSup(createOrdonnanceSup(consultation));

    return consultationRepository.save(consultation);
}



public DossierMedicalEntity createDossierMedical(
        CabinetEntity cabinet,
        ConsultationEntity consultation , 
        PatientEntity patient
) {
    

    // ======================
    // 2. DOSSIER MEDICAL
    // ======================
    DossierMedicalEntity dossier = new DossierMedicalEntity();
    dossier.setDateCreation(new Date());
    dossier.setStatut("ACTIF");
    dossier.setPatient(patient);

    // ======================
    // 3. ANTÉCÉDENTS MÉDICAUX
    // ======================
    AntecedentMedicalEntity am = new AntecedentMedicalEntity();
    am.setType("Diabète");
    am.setDescription("Diabète de type 2");
    am.setDateDebut(new Date());
    am.setDossierMedical(dossier);

    dossier.setAntecedentsMedicaux(List.of(am));

    // ======================
    // 4. ANTÉCÉDENTS CHIRURGICAUX
    // ======================
    AntecedentChirurgicalEntity ac = new AntecedentChirurgicalEntity();
    ac.setIntervention("Appendicectomie");
    ac.setAnnee(2018);
    ac.setComplications("Aucune");
    ac.setDossierMedical(dossier);

    dossier.setAntecedentsChirurgicaux(List.of(ac));

    // ======================
    // 5. ALLERGIES
    // ======================
    AllergieEntity allergie = new AllergieEntity();
    allergie.setSubstance("Pénicilline");
    allergie.setType("Médicamenteuse");
    allergie.setGravite("Modérée");
    allergie.setReaction("Éruption cutanée");
    allergie.setDossierMedical(dossier);

    dossier.setAllergies(List.of(allergie));

    // ======================
    // 6. TRAITEMENTS CHRONIQUES
    // ======================
    TraitementChroniqueEntity tc = new TraitementChroniqueEntity();
    tc.setMedicament("Metformine");
    tc.setDosage("850mg");
    tc.setFrequence("2 fois / jour");
    tc.setDateDebut(new Date());
    tc.setDossierMedical(dossier);

    dossier.setTraitementsChroniques(List.of(tc));

    // ======================
    // 7. HABITUDES DE VIE
    // ======================
    HabitudeVieEntity hv = new HabitudeVieEntity();
    hv.setDossierMedical(dossier);
    hv.setTabac("Non");
    hv.setAlcool("Occasionnel");
    hv.setAlimentation("Équilibrée");
    hv.setActivitePhysique("3 fois / semaine");
    hv.setSommeil("7h / nuit");

    dossier.setHabitudesVie(hv);

    // ======================
    // 8. CONSULTATIONS
    // ======================
    consultation.setDossierMedical(dossier);
    dossier.setConsultations(List.of(consultation));

    // ======================
    // 9. LIEN PATIENT <-> DOSSIER
    // ======================
    patient.setDossierMedical(dossier);

    dossier.setPatient(patient);

    // Cascade ALL → tout est persisté ici
    dossierMedicalRepository.save(dossier);

    patientRepo.save(patient); 

    return dossier;
}




// public void seedPatientFullData() {
//     CabinetEntity cabinet = createCabinet();
//     PatientEntity patient = createPatient(cabinet, "EL AMRANI");

   

//     RendezVousEntity pastRdv = createPastRendezVous(cabinet, patient);
//     createConsultation(pastRdv, dossier);

//     createFutureRendezVous(cabinet, patient);
// }



}
