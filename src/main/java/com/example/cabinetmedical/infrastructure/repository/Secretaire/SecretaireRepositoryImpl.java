package com.example.cabinetmedical.infrastructure.repository.Secretaire;

import com.example.cabinetmedical.domain.Repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.SecretaireMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecretaireRepositoryImpl implements SecretaireRepository {

    private SpringSecretaireRepository springRepo;
    private SecretaireMapper sm;

    public SecretaireRepositoryImpl(SpringSecretaireRepository springRepo, SecretaireMapper sm) {
        this.springRepo = springRepo;
        this.sm = sm;
    }

    public SecretaireEntity findById(int id) {
        return springRepo.findById(id).orElse(null);
    }
    public SecretaireEntity save(SecretaireEntity se) {return springRepo.save(se);}
    public List<SecretaireEntity> findByidCabinet(int idcabinet) {return springRepo.findByCabinet_idCabinet(idcabinet);}
    public void deleteByidSecretaire(int idsecretaire) { springRepo.deleteByidSecretaire(idsecretaire);}
    public List<SecretaireEntity> findAll(){return springRepo.findAll();}
}
