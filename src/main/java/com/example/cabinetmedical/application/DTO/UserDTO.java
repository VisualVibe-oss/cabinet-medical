package com.example.cabinetmedical.application.DTO;

import lombok.Data;


public class UserDTO {
    private int id  ; 
    private String email;
    private String role ;


    public void setId(int id){this.id = id;}
    public int getId(){return id;}



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


}
