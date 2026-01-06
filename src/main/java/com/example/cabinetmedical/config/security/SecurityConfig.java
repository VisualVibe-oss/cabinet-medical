package com.example.cabinetmedical.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework. context.annotation.Bean;
import org.springframework.context.annotation. Configuration;
import org.springframework. security.config.annotation.web. builders.HttpSecurity;
import org.springframework.security.config. annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password. PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util. Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
   
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/login" ,"/api/signup" ,"/api/refresh"  ).permitAll()
                        .requestMatchers("/api/logout").authenticated()
                        .requestMatchers("/api/medecin/**").hasRole("MEDECIN")
                        .requestMatchers("/api/consultation/**").hasRole("MEDECIN")
                        .requestMatchers("/api/secretaire/**").hasRole("SECRETAIRE")
                        .requestMatchers("/api/rendezvous/**").hasAnyRole("MEDECIN", "SECRETAIRE")
                        .requestMatchers("/api/dossierMedical/**").hasAnyRole("MEDECIN", "SECRETAIRE")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
    
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class  
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy. STATELESS));

        // ✅ NE PAS ajouter le JwtAuthenticationFilter pour les tests
        // . addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    
    

}
