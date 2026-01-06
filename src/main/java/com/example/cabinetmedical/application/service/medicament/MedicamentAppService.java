package com.example.cabinetmedical.application.service.medicament;

import com.example.cabinetmedical.application.dto.Medicament.MedicamentDTO;
import com.example.cabinetmedical.domain.model.medicament.Medicament;
import com.example.cabinetmedical.domain.service.medicament.MedicamentDomainService;
import com.example.cabinetmedical.infrastructure.entity.MedicamentEntity;
import com.example.cabinetmedical.infrastructure.mapper.medicament.MedicamentMapper;
import com.example.cabinetmedical.infrastructure.repository.medicament.MedicamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentAppService {

    private final MedicamentRepository repository;
    private final MedicamentMapper mapper;
    private final MedicamentDomainService domainService;

    public MedicamentAppService(MedicamentRepository repository, MedicamentMapper mapper, MedicamentDomainService medicamentDomainService) {
        this.repository = repository;
        this.mapper = mapper;
        this.domainService = medicamentDomainService;
    }

    public MedicamentDTO create(MedicamentDTO medicamentDTO){
        //dto domain
        Medicament medicament =mapper.toDomain(medicamentDTO);

        domainService.validate(medicament);

        if(repository.existsByNom(medicament.getNom())){
            throw new IllegalArgumentException("Nom existent");
        }

        //domain entity
        MedicamentEntity entity=mapper.toEntity(medicament);

        MedicamentEntity saved=repository.save(entity);

        //entity dto
        return mapper.toDto(saved);
    }

    public List<MedicamentDTO> listAll(){
        List<MedicamentEntity> entities=repository.findAll();
        return mapper.toDtoList(entities);
    }

    public MedicamentDTO oneMedicament(Integer id){
        MedicamentEntity entity=repository.findById(id).orElseThrow(()-> new RuntimeException("medicament non trouve pour ce id"));

        return mapper.toDto(entity);
    }

    public MedicamentDTO updateMedicament(Integer id, MedicamentDTO medicamentDTO){
        MedicamentEntity exists=repository.findById(id).orElseThrow(()->new RuntimeException("medicament non trouve pour ce id"));

        //dto  domain
        Medicament medicament =mapper.toDomain(medicamentDTO);
        medicament.setId(id);

        domainService.validate(medicament);

        if(!exists.getNom().equals(medicament.getNom()) && repository.existsByNom(medicament.getNom())){
            throw new IllegalArgumentException("Nom existent");
        }

        //domain to entity
        MedicamentEntity entity=mapper.toEntity(medicament);

        MedicamentEntity updated=repository.save(entity);

        return mapper.toDto(updated);
    }

    public void deleteMedicament(Integer id){
        MedicamentEntity entity=repository.findById(id).orElseThrow(()->new RuntimeException("medicament non trouve pour ce id"));

        //dto domain
        Medicament medicament=mapper.toDomain(entity);

        repository.deleteById(id);
    }

    public MedicamentDTO findMedicament(String nom){
        if(nom==null || nom.isEmpty()){
            return null;
        }
        MedicamentEntity entity=repository.findByNom(nom).orElseThrow(()->new RuntimeException("medicament non trouve pour ce nom"));
        return mapper.toDto(entity);
    }

}
