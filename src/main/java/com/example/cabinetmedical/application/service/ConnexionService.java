package com.example.cabinetmedical.application.service;

import com.example.cabinetmedical.application.dto.AuthResponse;
import com.example.cabinetmedical.application.dto.ConnexionRequest;
import com.example.cabinetmedical.config.security.JwtTokenProvider;
import com.example.cabinetmedical.infrastructure.entity.AdminEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.repository.AdminRepository;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConnexionService {
    public final AdminRepository adminRepository;
    public final MedecinRepository medecinRepository;
    public final SecretaireRepository secretaireRepository;
    public final PasswordEncoder passwordEncoder;
    public final JwtTokenProvider jwtTokenProvider;

    public AuthResponse connecter(ConnexionRequest request) {

        // Chercher l'utilisateur dans les 3 tables
        UserInfo userInfo = findUserByEmail(request.getEmail());

        if (userInfo == null) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getPassword(), userInfo.passwordHash)) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }

        // Générer le token JWT
        String token = jwtTokenProvider.generateTokenWithId(
                userInfo.id,
                userInfo.email,
                userInfo.role
        );

        // Construire et renvoyer la réponse
        return AuthResponse.builder()
                .id(userInfo.id)
                .email(userInfo.email)
                .nom(userInfo.nom)
                .prenom(userInfo.prenom)
                .telephone(userInfo.telephone)
                .signature(userInfo.signature)
                .role(userInfo.role)
                .token(token)
                .message("Connexion réussie")
                .build();
    }



    public Integer getMedecinIdByEmail(String email) {
        return medecinRepository.findByEmail(email)
                .map(MedecinEntity::getIdMedecin)
                .orElseThrow(() -> new IllegalArgumentException("Médecin non trouvé"));
    }


    private UserInfo findUserByEmail(String email) {
        // Chercher dans admins
        Optional<AdminEntity> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            AdminEntity a = admin.get();
            return new UserInfo(
                    a.getIdAdmin(),
                    a.getEmail(),
                    a.getPassword(),
                    a.getNom(),
                    a.getPrenom(),
                    a.getTelephone(),
                    null,
                    "ADMIN"
            );
        }

        // Chercher dans médecins
        Optional<MedecinEntity> medecin = medecinRepository.findByEmail(email);
        if (medecin.isPresent()) {
            MedecinEntity m = medecin.get();
            return new UserInfo(
                    m.getIdMedecin(),
                    m.getEmail(),
                    m.getPassword(),
                    m.getNom(),
                    m.getPrenom(),
                    m.getTelephone(),
                    m.getSignature(),
                    "MEDECIN"
            );
        }

        // Chercher dans secrétaires
        Optional<SecretaireEntity> secretaire = secretaireRepository.findByEmail(email);
        if (secretaire.isPresent()) {
            SecretaireEntity s = secretaire.get();
            return new UserInfo(
                    s.getIdSecretaire(),
                    s.getEmail(),
                    s.getPassword(),
                    s.getNom(),
                    s.getPrenom(),
                    s.getTelephone(),
                    null,
                    "SECRETAIRE"
            );
        }

        return null;
    }

    private static class UserInfo {
        Integer id;
        String email;
        String passwordHash;
        String nom;
        String prenom;
        String telephone;
        String signature;
        String role;

        UserInfo(Integer id, String email, String passwordHash, String nom,
                 String prenom, String telephone, String signature, String role) {
            this.id = id;
            this.email = email;
            this.passwordHash = passwordHash;
            this.nom = nom;
            this.prenom = prenom;
            this.telephone = telephone;
            this.signature = signature;
            this.role = role;
        }
    }
}
