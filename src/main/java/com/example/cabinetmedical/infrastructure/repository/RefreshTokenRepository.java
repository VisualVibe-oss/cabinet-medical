package com.example.cabinetmedical.infrastructure.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cabinetmedical.infrastructure.entity.RefreshTokenEntity;

import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Long> {

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    void deleteByExpiryDateBefore(Date currentDate);

    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

  

} 