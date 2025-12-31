package com.example.cabinetmedical.infrastructure.repository.RendezVous;

import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RendezVousRepositoryImpl implements RendezVousRepository {

    private SpringRendezVousRepository springRepo;

    public RendezVousRepositoryImpl(SpringRendezVousRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public RendezVousEntity save(RendezVousEntity rve) {
        return springRepo.save(rve);
    }

    @Override
    public List<RendezVousEntity> findAllByCabinet_idCabinet(int idCabinet) {
        return springRepo.findAllByCabinet_idCabinet(idCabinet);
    }

    @Override
    public RendezVousEntity findById(int idRendezVous) {
        return springRepo.findById(idRendezVous).get();
    }
}
