package com.example.cabinetmedical.application.DTO;

import lombok.Data;


public class UserDTO {
    private String email;
    private String role ;



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
