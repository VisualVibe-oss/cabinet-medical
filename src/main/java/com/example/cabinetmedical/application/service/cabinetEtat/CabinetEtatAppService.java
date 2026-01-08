package com.example.cabinetmedical.application.service.cabinetEtat;

import com.example.cabinetmedical.application.DTO.cabinetEtat.CabinetEtatDTO;
import com.example.cabinetmedical.application.DTO.cabinetEtat.CabinetEtatRequestDTO;
import com.example.cabinetmedical.application.DTO.cabinetEtat.CabinetEtatResponseDTO;
import com.example.cabinetmedical.domain.model.cabinetEtat.CabinetEtat;
import com.example.cabinetmedical.domain.service.cabinetEtat.CabinetEtatDomainService;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.mapper.cabinetEtat.CabinetEtatMapper;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CabinetEtatAppService {
    private final CabinetRepository repository ;
    private final CabinetEtatDomainService domainService ;
    private final CabinetEtatMapper mapper;

    public CabinetEtatAppService(CabinetRepository repository, CabinetEtatDomainService domainService, CabinetEtatMapper mapper) {
        this.repository = repository;
        this.domainService = domainService;
        this.mapper = mapper;
    }

    public List<CabinetEtatDTO> getAllCabinets(){
        return repository.findAll().stream()
                .map(CabinetEtatMapper::toDTO).collect(Collectors.toList());
    }

    public List<CabinetEtatDTO> getCabinetsActifs(){
        return repository.findByEtatTrue().stream()
                .map(CabinetEtatMapper::toDTO).collect(Collectors.toList());
    }

    public List<CabinetEtatDTO> getCabinetsInactifs(){
        return repository.findByEtatFalse().stream()
                .map(CabinetEtatMapper::toDTO).collect(Collectors.toList());
    }

    public CabinetEtatResponseDTO changerEtat(int id, CabinetEtatRequestDTO request){
        CabinetEntity entity=repository.findById(id).orElseThrow(()-> new RuntimeException("pas de cabinet avec ce id"));

        CabinetEtat cabinetEtat=mapper.toDomain(entity);

        domainService.validate(cabinetEtat);

        cabinetEtat.setEtat(request.isEstActif());

        mapper.updateEntity(cabinetEtat,entity);
        CabinetEntity savedCabinet = repository.save(entity);

        return CabinetEtatResponseDTO.builder()
                .idCabinet(savedCabinet.getIdCabinet())
                .nom(savedCabinet.getNom())
                .estActif(savedCabinet.getEtat())
                .build();
    }


}
