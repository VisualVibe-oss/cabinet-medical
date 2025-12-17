package com.example.cabinetmedical.exception;

import com.example.cabinetmedical.domain.utils.PermissionKey;

import java.security.Permission;

public class AbsentSecretaryPermissionError extends RuntimeException {
    public AbsentSecretaryPermissionError(PermissionKey key) {
        super("secretary does not have the permission " +key);
    }
}
