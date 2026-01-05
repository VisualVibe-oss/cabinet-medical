package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.domain.Repository.FactureRepository;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import com.example.cabinetmedical.infrastructure.mapper.FactureMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FactureAppService {
    private FactureMapper fm;
    private FactureRepository factureRepository;

    public FactureAppService(FactureMapper fm, FactureRepository factureRepository) {
        this.fm = fm;
        this.factureRepository = factureRepository;
    }
    public List<Facture> findAllbyCabinetAndMonth(int idCabinet, Date start, Date end){
    return fm.toDomainList(factureRepository.findbyidCabinetAndDateBetween(idCabinet, start, end));
    }
    public List<Facture> findAll(int idCabinet){
        return fm.toDomainList(factureRepository.findallbyidCabinet(idCabinet));
    }

    public Facture save(Facture facture){
        return fm.toDomain(factureRepository.save(fm.toEntity(facture)));
    }
}
