package com.example.cabinetmedical.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cabinetmedical.application.DTO.CabinetDTO;
import com.example.cabinetmedical.application.DTO.LoginDTO;
import com.example.cabinetmedical.application.DTO.MedecinDTO;
import com.example.cabinetmedical.application.DTO.SignupDataDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.exception.CredentialNotValidError;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.mapper.CabinetMapper;
import com.example.cabinetmedical.infrastructure.repository.MedecinRepository;
import com.example.cabinetmedical.infrastructure.repository.SecretaireRepository;
import com.example.cabinetmedical.infrastructure.repository.cabinet.CabinetRepository;

@Service
public class AuthService {

    @Autowired
    MedecinRepository medecinRepository;
     @Autowired
    CabinetRepository cabinetRepository;
     @Autowired
    SecretaireRepository secretaireRepository ; 
    
    public static final String medecinRole = "MEDECIN" ;
    public static final String secretaireRole = "SECRETAIRE" ;
    public static final String adminRole = "ADMIN" ;

     @Value("${MY_APP_ADMIN_USER}")
    private String adminUsername;

    @Value("${MY_APP_ADMIN_PASSWORD}")
    private String adminPassword;

    
    PasswordEncoder passwordEncoder;
    public AuthService(MedecinRepository medecinRepository, CabinetRepository cabinetRepository,
            PasswordEncoder passwordEncoder ,  SecretaireRepository secretaireRepository ) {

        this.medecinRepository = medecinRepository;
        this.cabinetRepository = cabinetRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretaireRepository  = secretaireRepository ; 
    }


    


    public void normaliseData(UserDTO user){
        //*  rendre l'email  en uppercase car il va reference sur l'utilsateur apres
        user.setEmail(user.getEmail().toUpperCase());
    }
    

    private void validateSignupData( MedecinDTO medecinDTO) {
        if(medecinRepository.existsByEmail(medecinDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if(medecinRepository.existsByTelephone(medecinDTO.getTelephone())) {
            throw new RuntimeException("Phone number already exists");
        }
    }

    public void signup(SignupDataDTO signupData) {

        MedecinDTO medecin = signupData.getMedecin();
        CabinetDTO cabinet = signupData.getCabinet();  

        medecin.setPassword(passwordEncoder.encode(medecin.getPassword()));

        
        validateSignupData(medecin);

        normaliseData(medecin);

        CabinetEntity cabinetEntity = CabinetMapper.DtoToEntity(medecin, cabinet);
        cabinetRepository.save(cabinetEntity);
        
    }   

    public UserDTO login(LoginDTO loginData) {
        String email = loginData.getEmail().toUpperCase();

        UserDTO user = new UserDTO();
        user.setEmail(email);

        // Vérifier si c'est une secrétaire
        secretaireRepository.findByEmail(email).ifPresent(secretaire -> {
            if (passwordEncoder.matches(loginData.getPassword(), secretaire.getPassword())) {
                user.setRole(secretaireRole);

            } else {
                throw new CredentialNotValidError("Email ou mot de passe incorrect");
            }
        });

        // Vérifier si c'est un médecin
        medecinRepository.findByEmail(email).ifPresent(medecin -> {
            if (passwordEncoder.matches(loginData.getPassword(), medecin.getPassword())) {
                user.setRole(medecinRole);
            } else {
                throw new CredentialNotValidError("Email ou mot de passe incorrect");
            }
        });

        if (user.getRole() == null) {
            throw new CredentialNotValidError("Email ou mot de passe incorrect");
        }

        return user;
    }




public UserDTO getUserDto(Authentication authentication) {

        String email = (String) authentication.getPrincipal();

        // 2. Récupérer le rôle et supprimer "ROLE_"
        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElse("NONE");
        UserDTO user = new UserDTO() ;
        user.setEmail(email);
        user.setRole(role);
        return user ; 
    }


       public UserDTO loginAdmin(LoginDTO loginData) {
        String email = loginData.getEmail();
        String password = loginData.getPassword() ;


        if(!password.equals(adminPassword) || !email.equals(adminUsername)){
            
            throw new CredentialNotValidError("Les credentiel que vous avez fournis ne sont pas juste") ;
        }

        UserDTO user = new UserDTO();
            user.setEmail(email);
            user.setRole(adminRole);
            

        return user ;
    }

}
