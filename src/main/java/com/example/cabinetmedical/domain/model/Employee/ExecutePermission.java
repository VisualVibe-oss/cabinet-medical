package com.example.cabinetmedical.domain.model.Employee;

import com.example.cabinetmedical.domain.utils.PermissionParameter;
import com.example.cabinetmedical.domain.utils.PermissionResponce;

public interface ExecutePermission<T,U> {
    PermissionResponce<T> doWork(PermissionParameter<U> param);
}
