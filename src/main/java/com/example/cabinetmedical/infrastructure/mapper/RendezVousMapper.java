package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
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

    public RendezVousEntity toEntity(RendezVous rv){


        RendezVousEntity rve = new RendezVousEntity();

        rve.setIdRendezVous(rv.getIdRendezVous());
        rve.setDateDebutRendezVous(rv.getDateDebutRendezVous());
        rve.setDateFinRendezVous(rv.getDateFinRendezVous());
        rve.setMotif(rv.getMotif());
        rve.setStatut(rv.getStatut());
        rve.setNotes(rv.getNotes());
        rve.setPatient(null);
        rve.setCabinet(this.cm.toEntity(rv.getCabinet()));

        return rve;
    }
    public RendezVous toDomain(RendezVousEntity rve){

        RendezVous rv = new RendezVous();

        rv.setIdRendezVous(rve.getIdRendezVous());
        rv.setDateDebutRendezVous(rve.getDateDebutRendezVous());
        rv.setDateFinRendezVous(rve.getDateFinRendezVous());
        rv.setMotif(rve.getMotif());
        rv.setStatut(rve.getStatut());
        rv.setNotes(rve.getNotes());
        rv.setPatient(null);
        rv.setCabinet(this.cm.toDomain(rve.getCabinet()));

        return rv;
    }

    public RendezVous toDomain(RendezVousDTO rvDTO){
        RendezVous rv = new RendezVous();

        rv.setIdRendezVous(rvDTO.getIdRendezVous());
        rv.setDateDebutRendezVous(rvDTO.getDateDebutRendezVous());
        rv.setDateFinRendezVous(rvDTO.getDateFinRendezVous());
        rv.setMotif(rvDTO.getMotif());
        rv.setStatut(rvDTO.getStatut());
        rv.setNotes(rvDTO.getNotes());

        return rv;
    }

    public RendezVousDTO toDTO(RendezVous rv){
        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rv.getIdRendezVous());
        rvDTO.setDateDebutRendezVous(rv.getDateDebutRendezVous());
        rvDTO.setDateFinRendezVous(rv.getDateFinRendezVous());
        rvDTO.setMotif(rv.getMotif());
        rvDTO.setStatut(rv.getStatut());
        rvDTO.setNotes(rv.getNotes());

        return rvDTO;
    }

    public RendezVousDTO toDTO(RendezVousEntity rve){

        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rve.getIdRendezVous());
        rvDTO.setDateDebutRendezVous(rve.getDateDebutRendezVous());
        rvDTO.setDateFinRendezVous(rve.getDateFinRendezVous());
        rvDTO.setMotif(rve.getMotif());
        rvDTO.setStatut(rve.getStatut());
        rvDTO.setNotes(rve.getNotes());

        return rvDTO;
    }

    public List<RendezVousDTO> toDTOList(List<RendezVousEntity> rveList){
        return rveList.stream().map(this::toDTO).toList();
    }

    public List<RendezVous> toDomainList(List<RendezVousDTO> rvdtoList){
        return rvdtoList.stream().map(this::toDomain).toList();
    }
}
