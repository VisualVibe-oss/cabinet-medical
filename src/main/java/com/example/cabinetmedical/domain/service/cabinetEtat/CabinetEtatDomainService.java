package com.example.cabinetmedical.domain.service.cabinetEtat;

import com.example.cabinetmedical.domain.model.cabinetEtat.CabinetEtat;
import org.springframework.stereotype.Service;

@Service
public class CabinetEtatDomainService {
    public void validate(CabinetEtat cabinetEtat){
        if(cabinetEtat == null){
            throw new IllegalArgumentException("Etat ne peut pas etre nulle");
        }
    }
}
