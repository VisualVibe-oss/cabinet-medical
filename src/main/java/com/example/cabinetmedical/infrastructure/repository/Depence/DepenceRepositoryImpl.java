package com.example.cabinetmedical.infrastructure.repository.Depence;

import com.example.cabinetmedical.domain.Repository.DepenceRepository;
import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.infrastructure.entity.DepenceEntity;
import com.example.cabinetmedical.infrastructure.entity.FactureEntity;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class DepenceRepositoryImpl implements DepenceRepository {
    private SpringDepenceRepository depenceRepository;


    public DepenceRepositoryImpl(SpringDepenceRepository depenceRepository) {
        this.depenceRepository = depenceRepository;
    }
    @Override
    public List<DepenceEntity> findbyidCabinetAndDateBetween(int idCabinet, Date start, Date end){
        return depenceRepository.findALLByCabinet_idCabinetAndDateBetween(idCabinet, start, end);
    }
    @Override
    public List<DepenceEntity> findallbyidCabinet(int idCabinet){
        return depenceRepository.findAllByCabinet_idCabinet(idCabinet);
    }
    @Override
    public DepenceEntity save(DepenceEntity de){
        return depenceRepository.save(de);
    }
    @Override
    public List<DepenceEntity> findByType(DepenceType type){
        return depenceRepository.findAllByType(type);
    }
    @Override
    public void delete(DepenceEntity de){
        depenceRepository.delete(de);
    }
    @Override
    public List<DepenceEntity> findAllByParent(DepenceEntity parent){
        return depenceRepository.findAllByParent(parent);
    }
    @Override
    public Optional<DepenceEntity> findById(int id){
        return depenceRepository.findById(id);
    }
}

