package com.example.cabinetmedical.config.cors;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // 👈 Import corrigé (pas de .reactive)

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 1. Autorise l'origine de ton Front-end
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); 
        
        // 2. Autorise les méthodes HTTP nécessaires
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        
        // 3. Autorise les headers (ajoute Set-Cookie ou Cookie si nécessaire, mais Content-Type/Auth suffisent souvent)
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin"));
        
        // 4. PERMET L'ENVOI DES COOKIES (Très important pour ton cas)
        configuration.setAllowCredentials(true); 
        
        // 5. Applique cette config à toutes les routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}