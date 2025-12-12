package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.repository.CabinetRepository;
import com.example.cabinetmedical.application.dto.AuthResponse;
import com.example.cabinetmedical.application.dto.MedecinInscriptionRequest;
import com.example.cabinetmedical.application.dto.SecretaireInscriptionRequest;
import com.example.cabinetmedical.config.security.JwtTokenProvider;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.AdminRepository;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscriptionService {
    public final AdminRepository adminRepository;
    public final MedecinRepository medecinRepository;
    public final SecretaireRepository secretaireRepository;
    public final PasswordEncoder passwordEncoder;
    public final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponse inscrireMedecin(MedecinInscriptionRequest req){
        if(emailExists(req.getEmail())){
            throw new IllegalArgumentException("Email existente");
        }

        CabinetEntity cabinet = new CabinetEntity();
        cabinet.setNom(req.getNomCabinet());
        cabinet.setSpecialite(req.getSpecialite());
        cabinet.setAdresse(req.getAdresseCabinet());
        cabinet.setTelephone(req.getTelCabinet());
        // cabinet.setLogo(req.getLogoCabinet());

        String passewordHash=passwordEncoder.encode(req.getPassword());
        MedecinEntity medecin=new MedecinEntity();
        medecin.setEmail(req.getEmail());
        medecin.setPassword(passewordHash);
        medecin.setNom(req.getNom().trim());
        medecin.setPrenom(req.getPrenom().trim());
        medecin.setSignature(req.getSignature());
        medecin.setTelephone(req.getTelephone());
        medecin.setCabinet(cabinet);
        cabinet.setMedecin(medecin);

        MedecinEntity medecinSaved=medecinRepository.save(medecin);


        String token=jwtTokenProvider.generateTokenWithId(medecinSaved.getIdMedecin(),
                medecinSaved.getEmail(),"medecin");

        return AuthResponse.builder()
                .id(medecinSaved.getIdMedecin())
                .email(medecinSaved.getEmail())
                .nom(medecinSaved.getNom())
                .prenom(medecinSaved.getPrenom())
                .telephone(medecinSaved.getTelephone())
                .signature(medecinSaved.getSignature())
                .role("medecin")
                .token(token)
                .message("Inscription reussie")
                .build();
    }

    @Transactional
    public AuthResponse inscrireSecretaire(SecretaireInscriptionRequest req,int idMedecin ){
        MedecinEntity medecin=medecinRepository.findById(idMedecin)
                .orElseThrow(()->new IllegalArgumentException("Medecin non trouve"));
        if(emailExists(req.getEmail())){
            throw new IllegalArgumentException("Email existente");
        }
        String passwordHash=passwordEncoder.encode(req.getPassword());
        SecretaireEntity secretaire = new SecretaireEntity();
        secretaire.setEmail(req.getEmail().toLowerCase().trim());
        secretaire.setPassword(passwordHash);
        secretaire.setNom(req.getNom().trim());
        secretaire.setPrenom(req.getPrenom().trim());
        secretaire.setSalaire(req.getSalaire());
        secretaire.setTelephone(req.getTelephone());

        if(medecin.getCabinet()!=null){
            secretaire.setCabinet(medecin.getCabinet());
        }
        SecretaireEntity secretaireSaved=secretaireRepository.save(secretaire);

        String token=jwtTokenProvider.generateTokenWithId(secretaireSaved.getIdSecretaire(),
                secretaireSaved.getEmail(),"secretaire");

        return AuthResponse.builder()
                .id(secretaireSaved.getIdSecretaire())
                .email(secretaireSaved.getEmail())
                .nom(secretaireSaved.getNom())
                .prenom(secretaireSaved.getPrenom())
                .telephone(secretaireSaved.getTelephone())
                .role("secretaire")
                .token(token)
                .message("secretaire inscrite avec succes")
                .build();
    }

    private boolean emailExists(String email){
        return  adminRepository.existsByEmail(email)||medecinRepository.existsByEmail(email)
                ||secretaireRepository.existsByEmail(email);
    }

}
