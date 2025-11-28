package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "action")
public class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAction;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "idAdmin")
    private AdminEntity admin;

    @ManyToOne()
    @JoinColumn(name = "idCabinet")
    private CabinetEntity cabinet;

    public ActionEntity(int idAction, String description, Date date, AdminEntity admin, CabinetEntity cabinet) {
        this.idAction = idAction;
        this.description = description;
        this.date = date;
        this.admin = admin;
        this.cabinet = cabinet;
    }
    public ActionEntity() {}

    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }
}
