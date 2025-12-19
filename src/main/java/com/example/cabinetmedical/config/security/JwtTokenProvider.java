package com.example.cabinetmedical.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.access.secret}")
    private String jwtAccesSecret;

    @Value("${jwt.refresh.secret}")
    private String jwtRefreshSecret;

    @Value("${jwt.access.expiration}")
    private long accesExpiration;

    // extraire l email du token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtAccesSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // extraire le role du token
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtAccesSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtAccesSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Vérifier l'expiration
            return claims.getExpiration().after(new Date());

        } catch (ExpiredJwtException e) {
            // Token expiré
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            // Token invalide (signature, format, etc.)
            return false;
        }
    }

}
