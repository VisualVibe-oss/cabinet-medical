package com.example.cabinetmedical.domain.utils;

public class PermissionParameter<T> {
    PermissionKey key ;
    T payload ;

    public PermissionParameter(PermissionKey key , T payload){
        this.key = key;
        this.payload = payload;
    }


    public PermissionKey getKey() {
        return key;
    }

    public T getPayload() {
        return payload;
    }

    public void setKey(PermissionKey key) {
        this.key = key;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
