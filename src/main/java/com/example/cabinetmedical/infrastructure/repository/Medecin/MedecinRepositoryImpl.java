package com.example.cabinetmedical.infrastructure.repository.Medecin;

import com.example.cabinetmedical.domain.Repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

<<<<<<< HEAD
    @Override
    public CabinetEntity findCabinetByMedecin(int idMedecin) {
       
        throw new UnsupportedOperationException("Unimplemented method 'findCabinetByMedecin'");
    }

    @Override
    public CabinetEntity findCabinetByMedecinEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCabinetByMedecinEmail'");
    }
    public List<MedecinEntity> findAll() {return springMedecinRepository.findAll();}
    public Optional<MedecinEntity> findByEmail(String email) {return springMedecinRepository.findByEmail(email);}
=======
   
>>>>>>> acf2c00 (feat: Add consultation creation functionality and related DTOs)
}
