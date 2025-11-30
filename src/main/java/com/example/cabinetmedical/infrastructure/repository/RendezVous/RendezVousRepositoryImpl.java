package com.example.cabinetmedical.infrastructure.repository.RendezVous;

import com.example.cabinetmedical.domain.Repository.RendezVousRepository;
import com.example.cabinetmedical.domain.model.rendezvous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RendezVousRepositoryImpl implements RendezVousRepository {

    private SpringRendezVousRepository springRepo;
    private RendezVousMapper rvm;



    @Override
    public RendezVousEntity save(RendezVousEntity rve) {
        return springRepo.save(rve);
    }
}
