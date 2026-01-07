package com.example.cabinetmedical.application.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.exception.CabinetBlockedExecption;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.Secretaire.SpringSecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MedecinRepository medecinRepository;
    private final SpringSecretaireRepository secretaireRepository;
    private final CabinetRepository cabinetRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String emailLower = email.toUpperCase().trim();


            Optional<MedecinEntity> medecin = medecinRepository.findByEmail(emailLower);
            if (medecin.isPresent()) {
                MedecinEntity m = medecin.get();
                boolean etat = cabinetRepository.findEtatByMedecin(m) ;
                if(etat){
                    return User.builder()
                        .username(m.getEmail())
                        .password(m.getPassword())
                        .roles("MEDECIN")
                        .build();
                }
                
                throw new CabinetBlockedExecption("Cette Cabinet est bloquer") ;
            }

            Optional<SecretaireEntity> secretaire = secretaireRepository.findByEmail(emailLower);
            if (secretaire.isPresent()) {
                SecretaireEntity s = secretaire.get();
                boolean etat = secretaireRepository.findEtatBySecretaireEmail(email) ;
                if(etat){
                    return User.builder()
                        .username(s.getEmail())
                        .password(s.getPassword())
                        .roles("SECRETAIRE")
                        .build();
                }
                
                   throw new CabinetBlockedExecption("Cette Cabinet est bloquer") ;

            }

            throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
        };
}