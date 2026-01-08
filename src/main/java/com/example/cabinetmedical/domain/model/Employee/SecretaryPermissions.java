package com.example.cabinetmedical.domain.model.Employee;

import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.permissions.rendezVous.CreerRendezVous;
import com.example.cabinetmedical.domain.utils.*;

import java.util.Map;
import java.util.function.Supplier;

public class SecretaryPermissions {
    private static final Map<PermissionKey, Supplier<ExecutePermission>> permissionRegistry = Map.of(
            PermissionKey.CREER_RENDEZ_VOUS, CreerRendezVous::new

    );

    public PermissionResponce<?> doWork(PermissionParameter<?> param) {
        if(!permissionRegistry.containsKey(param.getKey())) {
            throw new IllegalStateException("the registry does not contain the following key: " + param.getKey());
        }
        return  permissionRegistry.get(param.getKey()).get().doWork(param);
    }
}

