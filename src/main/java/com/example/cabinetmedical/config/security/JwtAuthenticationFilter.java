package com.example.cabinetmedical.config.security;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.CustomUserDetailsService;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
   private final MedecinRepository medecinRepository;
    private final SpringSecretaireRepository secretaireRepository;
    private final CustomUserDetailsService userDetailsService ; 

    private void valideRole(UserDetails user , String role ){

        if(role.equals(AuthService.secretaireRole)) {
            String email  = user.getUsername() ;
            Optional<SecretaireEntity> secretaireEntity = secretaireRepository.findByEmail(email) ;
            if(!secretaireEntity.isPresent()){
                throw new RuntimeException("Le role et l'email ne sont pas convenable") ;
            }

        }

        if(role.equals(AuthService.medecinRole)){
            String email  = user.getUsername() ;
            Optional <MedecinEntity> medcin =  medecinRepository.findByEmail(email) ;
            if(!medcin.isPresent()){
                throw new RuntimeException("Le role et l'email ne sont pas convenable") ;
            }
        }
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        try{
            String jwt=getTokenFromCookies(req);

            if(!StringUtils.hasText(jwt)) {
                chain.doFilter(req, res);
                return;
            }
            
            if(jwtTokenProvider.validateToken(jwt)) {
                String email=jwtTokenProvider.getEmailFromToken(jwt);
                String role=jwtTokenProvider.getRoleFromToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                valideRole(userDetails, role);

                SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+role);
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email,null,Collections.singletonList(authority));

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            
        }

        chain.doFilter(req, res);
    }



    

   private String getTokenFromCookies(HttpServletRequest request) {
    if (request.getCookies() == null) return null;

    for (Cookie cookie : request.getCookies()) {
        if ("accessToken".equals(cookie.getName())) {
            return cookie.getValue();
        }
    }
    return null;
}
}