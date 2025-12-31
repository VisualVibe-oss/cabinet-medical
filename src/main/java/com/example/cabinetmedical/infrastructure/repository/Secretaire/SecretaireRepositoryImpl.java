package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.domain.Repository.SecretaireRepository;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SecretaireRepositoryImpl implements SecretaireRepository {

    private SpringSecretaireRepository springRepo;
    private SecretaireMapper sm;

    public SecretaireRepositoryImpl(SpringSecretaireRepository springRepo, SecretaireMapper sm) {
        this.springRepo = springRepo;
        this.sm = sm;
    }

    public Optional<SecretaireEntity> findById(int id) {
        return springRepo.findById(id);
    }
    public SecretaireEntity save(SecretaireEntity se) {return springRepo.save(se);}

    @Override
    public CabinetEntity findCabinetBySecretaireId(int id) {
        return springRepo.findCabinetBySecretaireId(id) ;
    }
    public List<SecretaireEntity> findByidCabinet(int idcabinet) {return springRepo.findByCabinet_idCabinet(idcabinet);}
    public void delete(SecretaireEntity se) { springRepo.delete(se);}
    public List<SecretaireEntity> findAll(){return springRepo.findAll();}
}
