package com.example.cabinetmedical.application.service;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.application.DTO.DossierMedicalDetailDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.domain.model.DossierMedical.DossierMedical;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.model.patient.Patient;
import com.example.cabinetmedical.infrastructure.entity.DossierMedicalEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.DossierMedicalMapper;
import com.example.cabinetmedical.infrastructure.repository.DossierMedicalRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.RendezVousRepositoryImpl;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;

@Service
public class DossierMedicalService {
    private SpringRendezVousRepository rendezVousRepository;
    private  DossierMedicalRepository dossierMedicalRepository; 

    public DossierMedicalService(SpringRendezVousRepository rendezVousRepositoryImp ,  DossierMedicalRepository dossierMedicalRepository) {
        this.rendezVousRepository = rendezVousRepositoryImp;
        this.dossierMedicalRepository = dossierMedicalRepository ;
        
    }

    


    public DossierMedicalDetailDTO getDossierMedicalDetail(UserDTO user, int idRendezVous) {

        // je doit recupere le cabinet 


        //  verifier le behaviro pack 


        // perform work

        PatientEntity patientEntity = rendezVousRepository.findPatientByIdRendezVous(idRendezVous)
            .orElseThrow(() -> new RuntimeException("RendezVous not found with id: " + idRendezVous));

        DossierMedicalEntity     dossierMedicalEntity = dossierMedicalRepository.findByPatient_IdPatient(patientEntity.getIdPatient())
            .orElseThrow(() -> new RuntimeException("DossierMedical not found for patient id: " + patientEntity.getIdPatient()));

        
       DossierMedicalDetailDTO detailDTO = DossierMedicalMapper.toDetailDTO(dossierMedicalEntity); 

        return detailDTO ;
       

    }

    

}
