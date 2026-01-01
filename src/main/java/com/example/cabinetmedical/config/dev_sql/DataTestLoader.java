package com.example.cabinetmedical.config.dev_sql;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.utils.PackKey;
import com.example.cabinetmedical.domain.utils.RendezVousState;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.OffreEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
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
    private SecretaireRepository secretaireRepo;
    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private SpringRendezVousRepository rdvRepo;
    @Autowired 
    private OffreRepository offreRepository ; 
    @Autowired 
    private PasswordEncoder passwordEncoder ;

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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 7);

        Date dateSemaineProchaine = cal.getTime();
        rdv.setDateRendezVous(dateSemaineProchaine);
        rdv.setMotif("Consultation de routine");
        rdv.setStatut(RendezVousState.SCHEDULED);
        rdv.setNotes("Pas de notes particulières");
        rdv.setCabinet(cabinet);
        rdv.setConsultationType("Visite Hebdomadaire");
        rdv.setPatient(patient);
        return rdvRepo.save(rdv);
    }
}
