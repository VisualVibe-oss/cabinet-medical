package com.example.cabinetmedical.infrastructure.repository.Medecin;

import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.domain.model.Medecin.Medecin;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MedecinRepositoryImpl implements MedecinRepository {
    private SpringMedecinRepository springMedecinRepository ;

    public MedecinRepositoryImpl(SpringMedecinRepository springMedecinRepository) {
        this.springMedecinRepository = springMedecinRepository;
    }

    @Override
    public MedecinEntity find(int id) {
       return springMedecinRepository.findById(id).orElse(null);
    }
}
