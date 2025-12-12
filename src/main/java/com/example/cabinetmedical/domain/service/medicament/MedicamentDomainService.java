package com.example.cabinetmedical.domain.service.medicament;

import com.example.cabinetmedical.domain.model.medicament.Medicament;
import org.springframework.stereotype.Service;

@Service
public class MedicamentDomainService {

    public void validate(Medicament medicament){
        if(medicament.getNom() == null || medicament.getNom().isEmpty()){
            throw new IllegalArgumentException("Nom vide");
        }
    }
}
