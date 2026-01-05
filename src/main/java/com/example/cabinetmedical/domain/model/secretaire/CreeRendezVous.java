package com.example.cabinetmedical.domain.model.secretaire;

import com.example.cabinetmedical.domain.model.Employee.ExecutePermission;
import com.example.cabinetmedical.domain.model.RendezVous.RendezVous;
import com.example.cabinetmedical.domain.utils.*;

public class CreeRendezVous implements ExecutePermission<RendezVous,RendezVous> {

    @Override
    public PermissionResponce<RendezVous> doWork(PermissionParameter<RendezVous> param){
        RendezVous rendezVousPayload = (RendezVous) param.getPayload();
        return new PermissionResponce<RendezVous>(PermissionKey.CREE_RENDEZ_VOUS, rendezVousPayload);

    }
}
