package com.example.cabinetmedical.config.security;

import com.example.cabinetmedical.infrastructure.entity.AdminEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.AdminRepository;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AdminRepository adminRepository;
    private final MedecinRepository medecinRepository;
    private final SecretaireRepository secretaireRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/signup").permitAll()
                        .requestMatchers("/api/logout").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class  
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            String emailLower = email.toLowerCase().trim();

            Optional<AdminEntity> admin = adminRepository.findByEmail(emailLower);
            if (admin.isPresent()) {
                AdminEntity a = admin.get();
                return User.builder()
                        .username(a.getEmail())
                        .password(a.getPassword())
                        .roles("ADMIN")
                        .build();
            }

            Optional<MedecinEntity> medecin = medecinRepository.findByEmail(emailLower);
            if (medecin.isPresent()) {
                MedecinEntity m = medecin.get();
                return User.builder()
                        .username(m.getEmail())
                        .password(m.getPassword())
                        .roles("MEDECIN")
                        .build();
            }

            Optional<SecretaireEntity> secretaire = secretaireRepository.findByEmail(emailLower);
            if (secretaire.isPresent()) {
                SecretaireEntity s = secretaire.get();
                return User.builder()
                        .username(s.getEmail())
                        .password(s.getPassword())
                        .roles("SECRETAIRE")
                        .build();
            }

            throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
        };
    }



    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOriginPattern("*"); // ou ton domaine : "http://localhost:3000"
        config.addAllowedMethod("*"); // GET, POST, PUT, DELETE...
        config.addAllowedHeader("*"); // tous les headers
        config.setAllowCredentials(true); // autorise cookies / tokens dans les requêtes

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
