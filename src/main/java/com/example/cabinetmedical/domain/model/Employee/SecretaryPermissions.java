package com.example.cabinetmedical.domain.model.Employee;

import com.example.cabinetmedical.domain.utils.*;

import java.util.Map;

public class SecretaryPermissions {
    private Map<PermissionKey, ExecutePermission> registry;

    public SecretaryPermissions(Map<PermissionKey, ExecutePermission> registry) {
        this.registry = registry;
    }

    public PermissionResponce<?> doWork(PermissionParameter<?> param) {

        if(!registry.containsKey(param.getKey())) {
            throw new IllegalStateException("the registry does not contain the following key: " + param.getKey());
        }
        return  registry.get(param.getKey()).doWork(param);
    }
}
