package com.example.cabinetmedical.infrastructure.mapper.secretaire;

import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Employee.SecretaryPermissions;
import com.example.cabinetmedical.domain.model.secretaire.Secretaire;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.cabinet.CabinetMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class SecretaireMapper {

    private final CabinetMapper cabinetMapper;
    private final SecretaryPermissions secretaryPermissions;

    public SecretaireMapper(CabinetMapper cabinetMapper,
                            SecretaryPermissions secretaryPermissions) {
        this.cabinetMapper = cabinetMapper;
        this. secretaryPermissions = secretaryPermissions;
    }

    /**
     * Entity → Domain
     * Injecte automatiquement le registry de permissions
     */
    public Secretaire toDomain(SecretaireEntity entity) {
        if (entity == null) return null;

        // Convertir le cabinet
        Cabinet cabinet = null;
        if (entity.getCabinet() != null) {
            cabinet = cabinetMapper. toDomain(entity.getCabinet());
        }

        // Récupérer les permissions (ou créer un set vide si null)
        var permissionKeys = entity.getPermissionKeys();
        if (permissionKeys == null) {
            permissionKeys = new HashSet<>();
        }

        // Créer le modèle de domaine
        Secretaire secretaire = new Secretaire(
                entity.getIdSecretaire(),
                entity. getNom(),
                entity.getPrenom(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getSalaire(),
                entity.getTelephone(),
                cabinet,
                permissionKeys
        );

        // ✅ Injecter le registry de permissions
        secretaire.setSecretaryPermissions(this.secretaryPermissions);

        return secretaire;
    }

    /**
     * Domain → Entity
     */
    public SecretaireEntity toEntity(Secretaire domain) {
        if (domain == null) return null;

        SecretaireEntity entity = new SecretaireEntity();
        entity.setIdSecretaire(domain.getIdSecretaire());
        entity.setNom(domain.getNom());
        entity.setPrenom(domain. getPrenom());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setSalaire(domain.getSalaire());
        entity.setTelephone(domain.getTelephone());
        entity.setPermissionKeys(domain.getPermissionKeys());

        return entity;
    }
}