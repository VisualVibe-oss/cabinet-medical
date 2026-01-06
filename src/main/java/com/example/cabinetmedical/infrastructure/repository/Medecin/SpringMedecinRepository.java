package com.example.cabinetmedical.infrastructure.repository.Medecin;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringMedecinRepository extends JpaRepository<MedecinEntity,Integer> {

    
    Optional<MedecinEntity> findByEmail(String email);
    
    @Query("select m.cabinet from MedecinEntity m where m.email =:email")
    CabinetEntity findCabinetByEmail(@Param("email") String email);


    CabinetEntity findCabinetByIdMedecin(int idMedecin);

}
