package com.example.cabinetmedical.infrastructure.mapper;

import com.example.cabinetmedical.application.dto.RendezVousDTO;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import org.springframework.stereotype.Component;

//TEMPORARY
@Component
public class RendezVousMapper {

    private PatientMapper pm;
    private CabinetMapper cm;

    public RendezVousEntity toEntity(RendezVous rv){


        RendezVousEntity rve = new RendezVousEntity();

        rve.setIdRendezVous(rv.getIdRendezVous());
        rve.setDateRendezVous(rv.getDateRendezVous());
        rve.setMotif(rv.getMotif());
        rve.setStatut(rv.getStatut());
        rve.setNotes(rv.getNotes());
        rve.setPatient(this.pm.toEntity(rv.getPatient()));
        rve.setCabinet(this.cm.toEntity(rv.getCabinet()));

        return rve;
    }
    public RendezVous toDomain(RendezVousEntity rve){

        RendezVous rv = new RendezVous();

        rv.setIdRendezVous(rve.getIdRendezVous());
        rv.setDateRendezVous(rve.getDateRendezVous());
        rv.setMotif(rve.getMotif());
        rv.setStatut(rve.getStatut());
        rv.setNotes(rve.getNotes());
        rv.setPatient(this.pm.toDomain(rve.getPatient()));
        rv.setCabinet(this.cm.toDomain(rve.getCabinet()));

        return rv;
    }

    public RendezVous toDomain(RendezVousDTO rvDTO){
        RendezVous rv = new RendezVous();

        rv.setIdRendezVous(rvDTO.getIdRendezVous());
        rv.setDateRendezVous(rvDTO.getDateRendezVous());
        rv.setMotif(rvDTO.getMotif());
        rv.setStatut(rvDTO.getStatut());
        rv.setNotes(rvDTO.getNotes());

        return rv;
    }

    public RendezVousDTO toDTO(RendezVous rv){
        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rv.getIdRendezVous());
        rvDTO.setDateRendezVous(rv.getDateRendezVous());
        rvDTO.setMotif(rv.getMotif());
        rvDTO.setStatut(rv.getStatut());
        rvDTO.setNotes(rv.getNotes());

        return rvDTO;
    }

    public RendezVousDTO toDTO(RendezVousEntity rve){

        RendezVousDTO rvDTO = new RendezVousDTO();

        rvDTO.setIdRendezVous(rve.getIdRendezVous());
        rvDTO.setDateRendezVous(rve.getDateRendezVous());
        rvDTO.setMotif(rve.getMotif());
        rvDTO.setStatut(rve.getStatut());
        rvDTO.setNotes(rve.getNotes());

        return rvDTO;
    }
}
