package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.stereotype.Component;

import java.util.List;

//TEMPORARY
@Component
public class RendezVousMapper {

    private PatientMapper pm;
    private CabinetMapper cm;

    public RendezVousMapper(PatientMapper pm, CabinetMapper cm) {
        this.pm = pm;
        this.cm = cm;
    }

  

    public static RendezVousEntity toEntity(RendezVous rv) {

        RendezVousEntity rve = new RendezVousEntity();

        rve.setIdRendezVous(rv.getIdRendezVous());
        rve.setDateDebutRendezVous(rv.getDateDebutRendezVous());
        rve.setDateFinRendezVous(rv.getDateFinRendezVous());
        rve.setMotif(rv.getMotif());
        rve.setConsultationType(rv.getConsultationType());
        rve.setStatut(rv.getStatut());
        rve.setPrix(rv.getPrix());
        rve.setNotes(rv.getNotes());
        // Vérification pour le Patient
        rve.setPatient(null);


        // Vérification pour le Cabinet
        //if (rv.getCabinet() != null) {
        //    rve.setCabinet(CabinetMapper.toEntity(rv.getCabinet()));
      //  } else {
            rve.setCabinet(null);
        //}
        return rve;
    }

    public static RendezVous toDomain(RendezVousDTO rvDTO) {
        RendezVous rv = new RendezVous();

        rv.setIdRendezVous(rvDTO.getIdRendezVous());
        rv.setDateDebutRendezVous(rvDTO.getDateDebutRendezVous());
        rv.setDateFinRendezVous(rvDTO.getDateFinRendezVous());
        rv.setMotif(rvDTO.getMotif());
        rv.setPrix(rvDTO.getPrix());
        rv.setConsultationType(rvDTO.getConsultationType());
        rv.setStatut(rvDTO.getStatut());
        rv.setNotes(rvDTO.getNotes());

        return rv;
    }

    public static RendezVousDTO toDTO(RendezVous rv) {
        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rv.getIdRendezVous());
        rvDTO.setDateDebutRendezVous(rv.getDateDebutRendezVous());
        rvDTO.setDateFinRendezVous(rv.getDateFinRendezVous());
        rvDTO.setMotif(rv.getMotif());
        rvDTO.setConsultationType(rv.getConsultationType());
        rvDTO.setStatut(rv.getStatut());
        rvDTO.setNotes(rv.getNotes());
        rvDTO.setPrix(rv.getPrix());

        return rvDTO;
    }

    public RendezVousDTO toDTO(RendezVousEntity rve) {

        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rve.getIdRendezVous());
        rvDTO.setDateDebutRendezVous(rve.getDateDebutRendezVous());
        rvDTO.setDateFinRendezVous(rve.getDateFinRendezVous());
        rvDTO.setMotif(rve.getMotif());
        rvDTO.setConsultationType(rve.getConsultationType());
        rvDTO.setStatut(rve.getStatut());
        rvDTO.setNotes(rve.getNotes());
        rvDTO.setPrix(rve.getPrix());


        return rvDTO;
    }

    public static RendezVous toDomain(RendezVousEntity entity) {
        if (entity == null) {
            return null;
        }

        RendezVous domain = new RendezVous();

        // Mappage des champs simples
        domain.setIdRendezVous(entity.getIdRendezVous());
        domain.setDateDebutRendezVous(entity.getDateDebutRendezVous());
        domain.setDateFinRendezVous(entity.getDateFinRendezVous());
        domain.setMotif(entity.getMotif());
        domain.setConsultationType(entity.getConsultationType());
        domain.setStatut(entity.getStatut());
        domain.setPrix(entity.getPrix());
        domain.setNotes(entity.getNotes());


        if (entity.getCabinet() != null) {
            domain.setCabinet(CabinetMapper. toDomain(entity.getCabinet()));
        }

        if (entity.getPatient() != null) {
            domain.setPatient(PatientMapper.toDomain(entity.getPatient()));
        }

        // Mappage des relations complexes
        // On vérifie si l'entité n'est pas nulle avant d'appeler les autres mappers

        return domain;
    }
    public List<RendezVousDTO> toDTOList(List<RendezVousEntity> rveList){
          return rveList.stream().map(this::toDTO).toList();
} 
public List<RendezVous> toDomainList(List<RendezVousDTO> rvdtoList){
          return rvdtoList.stream().map(RendezVousMapper::toDomain).toList();
} 
}
