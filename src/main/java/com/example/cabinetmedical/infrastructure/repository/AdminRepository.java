package com.example.cabinetmedical.infrastructure.repository;

import com.example.cabinetmedical.infrastructure.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    Optional<AdminEntity> findByEmail(String email);
}
