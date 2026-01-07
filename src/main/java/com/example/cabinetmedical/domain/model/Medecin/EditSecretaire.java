package com.example.cabinetmedical.domain.model.Medecin;

import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import com.example.cabinetmedical.domain.utils.payload.AddSecretairePayload;
import com.example.cabinetmedical.domain.utils.payload.EditSecretairePayload;

import java.util.Set;

public class EditSecretaire implements Functionnalitie {

    @Override
    public FeatureResponce performWork(FeatureParameter param){
        if (param==null || param.getPayload()==null){
            throw new IllegalArgumentException("param or the payload is null");
        }
        if (!(param.getPayload() instanceof EditSecretairePayload)){
            throw new IllegalArgumentException("the payload is not of type EditSecretairePayload");
        }
       EditSecretairePayload p = (EditSecretairePayload) param.getPayload();
       Secretaire s = p.getSecretaire();
       if (s == null){
           throw new IllegalArgumentException("secretaire is null");
       }

        if (p.getSalaire() < 0) {
            throw new IllegalArgumentException("invalid salary");
        }
        String email=p.getSecretaire().getEmail();

        if (p.getExistingSecretaireEmails()!=null){
            boolean isEmilExists = p.getExistingSecretaireEmails().stream().anyMatch(existingEmail->existingEmail!=null && existingEmail.equals(email));
            if (isEmilExists){
                return new FeatureResponce(param.getKey(), "the email already exists");
            }
        }
        if (p.getExistingMedecinEmails()!=null) {
            boolean isEmilExists = p.getExistingMedecinEmails().stream().anyMatch(existingEmail -> existingEmail != null && existingEmail.equals(email));
            if (isEmilExists) {
                return new FeatureResponce(param.getKey(), "the email already exists");
            }
        }


        s.setNom(p.getNom());
        s.setPrenom(p.getPrenom());
        s.setEmail(p.getEmail());
        s.setTelephone(p.getTelephone());
        s.setSalaire(p.getSalaire());

        reconcilePermissions(s, p.getPermissions());

        return new FeatureResponce<>(param.getKey(),s);
    }

    private void reconcilePermissions(
            Secretaire s,
            Set<PermissionKey> requested
    ) {
        Set<PermissionKey> current = s.getPermissionKeys();

        current.stream()
                .filter(p -> !requested.contains(p))
                .forEach(s::revokePermission);

        requested.stream()
                .filter(p -> !current.contains(p))
                .forEach(s::grantPermission);
    }
}
