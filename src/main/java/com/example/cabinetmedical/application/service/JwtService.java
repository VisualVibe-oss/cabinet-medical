package com.example.cabinetmedical.application.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.infrastructure.entity.RefreshTokenEntity;
import com.example.cabinetmedical.infrastructure.repository.RefreshTokenRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import io.jsonwebtoken.Claims;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.access.secret}")
    private String accessSecret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.secret}")
    private String refreshSecret;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    // generer le token par id
    public String generateAccesToken(UserDTO user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessExpiration);

        Key key = Keys.hmacShaKeyFor(accessSecret.getBytes());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // * cette methode supprime l'ancien refreshToken et generer un nouveau */
    public String generateAndSaveRefreshToken(UserDTO user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshExpiration);
        Key key = Keys.hmacShaKeyFor(refreshSecret.getBytes());
        String refreshToken = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        refreshTokenRepository.deleteByEmail(user.getEmail());
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setRefreshToken(refreshToken);
        refreshTokenEntity.setEmail(user.getEmail());
        refreshTokenEntity.setExpiryDate(expiryDate);

        refreshTokenRepository.save(refreshTokenEntity);

        return refreshToken;
    }

    @Transactional
    private void validateRefreshToken(String refreshToken) {

        // 1️⃣ Vérifier signature + structure JWT
        Key key = Keys.hmacShaKeyFor(refreshSecret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        // 2️⃣ Récupérer l’email depuis le token
        String email = claims.getSubject();

        // 3️⃣ Vérifier présence en base
        RefreshTokenEntity tokenEntity = refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(() -> new BadCredentialsException("Refresh token inexistant"));

        // 4️⃣ Vérifier expiration (JWT + base)
        Date now = new Date();
        if (tokenEntity.getExpiryDate().before(now)) {
            refreshTokenRepository.delete(tokenEntity);
            throw new BadCredentialsException("Refresh token expiré");
        }

    }

    private UserDTO tokenToUserDTO(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(refreshSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject(); // récupère le sujet (email)
        String role = claims.get("role", String.class);

        UserDTO user = new UserDTO();
        user.setEmail(email);
        user.setRole(role);
        return user;
    }

    public String refreshToken(String token) {

        validateRefreshToken(token);
        UserDTO user = tokenToUserDTO(token) ;
        return  generateAccesToken(user);
    }

}
