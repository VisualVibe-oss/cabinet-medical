package com.example.cabinetmedical.infrastructure.repository.Facture;

import com.example.cabinetmedical.domain.Repository.FactureRepository;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FactureRepositoryImpl implements FactureRepository {
    SpringFactureRepository springFactureRepository;
    public FactureRepositoryImpl(SpringFactureRepository springFactureRepository) {
        this.springFactureRepository = springFactureRepository;
    }

    @Override
    public List<FactureEntity> findbyidCabinetAndDateBetween(int idCabinet, Date start, Date end){
        return springFactureRepository.findAllByCabinet_idCabinetAndDateBetween(idCabinet,start,end);
    }
    @Override
    public List<FactureEntity> findallbyidCabinet(int idCabinet){
        return springFactureRepository.findAllByCabinet_idCabinet(idCabinet);
    }
    @Override
    public FactureEntity save(FactureEntity factureEntity) {
        return springFactureRepository.save(factureEntity);
    }
}
