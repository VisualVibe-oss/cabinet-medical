package com.example.cabinetmedical.domain.utils;

public enum DepenceType {
    PERIODIC("Periodic"),
    INITIAL_PERIODIC("INITIAL_PERIODIC"),
    ONE_TIME("One Time");

    private String name;
    DepenceType(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

}
