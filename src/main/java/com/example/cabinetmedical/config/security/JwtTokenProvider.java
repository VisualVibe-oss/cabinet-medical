package com.example.cabinetmedical.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long expiration;

    //generer le token par id
    public String generateTokenWithId(int userId,String email,String role){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.builder()
                .setSubject(email)
                .claim("userId",userId)
                .claim("role",role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //extraire l email du token
    public String getEmailFromToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    //extraire le role du token
    public String getRoleFromToken(String token){
        Claims claims=Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
        return claims.get("role",String.class);
    }

    //extraire id du token
    public int getIdFromToken(String token){
        Claims claims=Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes())
                .build().parseClaimsJws(token).getBody();
        return claims.get("userId",Integer.class);
    }

    //validitite du token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes())
                    .build().parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
