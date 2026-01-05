package com.example.cabinetmedical.infrastructure.entity;

import jakarta.persistence.*;
import com.example.cabinetmedical.domain.utils.PermissionKey;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "secretaire")
public class SecretaireEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idSecretaire;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Float salaire;

    private String telephone;

    @ManyToOne
    @JoinColumn(name = "idCabinet")
    private CabinetEntity cabinet;



    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(
            name = "secretaire_permissions",
            joinColumns = @JoinColumn(name = "id_secretaire"))
    @Enumerated (EnumType.STRING)
    @Column(name = "permission_key")
    private Set<PermissionKey> permissionKeys = new HashSet<>();

    public Set<PermissionKey> getPermissionKeys() {
        return permissionKeys;
    }

    public void setPermissionKeys(Set<PermissionKey> permissionKeys) {
        this.permissionKeys = permissionKeys;
    }

    public SecretaireEntity(int idSecretaire, String nom, String prenom, String email, String password, Float salaire, String telephone, CabinetEntity cabinet) {
        this.idSecretaire = idSecretaire;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.salaire = salaire;
        this.telephone = telephone;
        this.cabinet = cabinet;
    }

    public SecretaireEntity(){}

    public CabinetEntity getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetEntity cabinet) {
        this.cabinet = cabinet;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSecretaire() {
        return idSecretaire;
    }

    public void setIdSecretaire(int idSecretaire) {
        this.idSecretaire = idSecretaire;
    }
}
