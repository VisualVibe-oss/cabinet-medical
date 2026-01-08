package com.example.cabinetmedical.domain.utils.payload;

import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.domain.utils.PermissionParameter;

public class SecretaryCheck {
    Secretaire secretaire;
    PermissionParameter permissionParameter;
    public SecretaryCheck(Secretaire secretaire, PermissionParameter permissionParameter){
        this.secretaire = secretaire;
        this.permissionParameter = permissionParameter;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public PermissionParameter getPermissionParameter() {
        return permissionParameter;
    }

    public void setPermissionParameter(PermissionParameter permissionParameter) {
        this.permissionParameter = permissionParameter;
    }
}
