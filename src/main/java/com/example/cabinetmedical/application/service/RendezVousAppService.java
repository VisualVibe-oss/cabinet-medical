package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.mapper.PatientMapper;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.RendezVousRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousAppService {

    private RendezVousRepositoryImpl rendezVousRepository;
    private CabinetRepository cabinetRepository;
    private RendezVousMapper rvm;
    private CabinetMapper cm;
    private PatientMapper pm;

    public RendezVousAppService(RendezVousRepositoryImpl rendezVousRepository, CabinetRepository cabinetRepository, RendezVousMapper rvm, CabinetMapper cm, PatientMapper pm ) {
        this.rendezVousRepository = rendezVousRepository;
        this.cabinetRepository = cabinetRepository;
        this.rvm = rvm;
        this.cm = cm;
        this.pm = pm;
    }

    public RendezVousDTO create(RendezVous rv, int idCabinet) {

        RendezVousEntity rve = rvm.toEntity(rv);
        rve.setCabinet(cabinetRepository.findByIdCabinet(idCabinet));
        rve.setPatient(pm.toEntity(rv.getPatient()));
        rendezVousRepository.save(rve);
        RendezVousDTO rvdto = rvm.toDTO(rve);
        rvdto.setIdCabinet(idCabinet);
        rvdto.setPatient(rv.getPatient());


        return rvdto;
    }

    public List<RendezVousDTO> getAllRendezVous(int idCabinet){
        List<RendezVousEntity> rves = rendezVousRepository.findAllByCabinet_idCabinet(idCabinet);
        List<RendezVousDTO> rvs = rvm.toDTOList(rves);
        for (int i = 0; i < rvs.size(); i++) {
            RendezVousEntity entity = rves.get(i);
            RendezVousDTO dto = rvs.get(i);

            if (entity.getPatient() != null) {
                dto.setPatient(pm.toDomain(entity.getPatient()));
            }
        }
        System.out.println("getAllRendezVous "+rvs);
        return rvs;
    }

    public RendezVous getRendezVous(int idRendezVous){
        return rvm.toDomain(rendezVousRepository.findById(idRendezVous));
    }
}
