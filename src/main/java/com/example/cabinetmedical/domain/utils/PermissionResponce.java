package com.example.cabinetmedical.domain.utils;

public class PermissionResponce<T> {
    PermissionKey key ;
    T payload ;

    public PermissionResponce(PermissionKey key , T payload){
        this.key = key;
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }
}
