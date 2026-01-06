package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.DTO.*;
import com.example.cabinetmedical.application.DTO.Stats.StatsDTO;
import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.CabinetAppService;
import com.example.cabinetmedical.application.service.MedecinAppService;
import com.example.cabinetmedical.domain.model.Cabinet.Cabinet;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.utils.PermissionKey;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {

    private MedecinAppService medecinAppService;
    private  AuthService authService ; 
    private  CabinetAppService cabinetAppService ;
    PasswordEncoder passwordEncoder;

    public MedecinController(MedecinAppService medecinAppService 
        ,AuthService authService , 
        CabinetAppService cabinetAppService,
                             PasswordEncoder passwordEncoder
      ) {
        this.medecinAppService = medecinAppService;
        this.authService = authService  ;
        this.cabinetAppService  =cabinetAppService ;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/addSecretary")
    public ApiResponse<SecretaireDTO> addSecretary(Authentication aut,@RequestBody CreateSecretaireDTO DTO) {

        System.out.println("RECEVIED SECRETAIRE: "+DTO );

        UserDTO user = authService.getUserDto(aut);

        DTO.setPassword(passwordEncoder.encode(DTO.getPassword()));

        Object result = medecinAppService.addSecretary(DTO, user);

        //* Valider le preseence du id  */
        Cabinet cabinet = cabinetAppService.getCabinetByEmail(user) ;
        int idCabinet =  cabinet.getIdCabinet() ;
        if (result instanceof SecretaireDTO secretaire) {
            String message = "Secretary: " + secretaire.getIdSecretaire() + " added for: " + idCabinet;
            return new ApiResponse<>(HttpStatus.OK.value(), message, secretaire);
        } else if (result instanceof String msg) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), msg, null);
        } else {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error", null);
        }
    }
    @GetMapping("/secretaires")
    public ApiResponse<List<SecretaireDTO>> getAllSecretaries(Authentication aut) {
        UserDTO user = authService.getUserDto(aut);
        int idCabinet = cabinetAppService.getCabinetByEmail(user).getIdCabinet() ;

        
        List<SecretaireDTO> secretaires = medecinAppService.getAllSecretaries(idCabinet);
        String message = "Secretary page accessed for office: " +  idCabinet;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status,message,secretaires);
    }

    @GetMapping("/secretaires/permissions")
    public ApiResponse<List<String>> getAllPermissions(Authentication aut) {

        UserDTO user = authService.getUserDto(aut);
        Cabinet cabinet = cabinetAppService.getCabinetByEmail(user);

        List<PermissionKey> permissions =
                medecinAppService.getPermissions(cabinet);

        List<String> permissionNames = permissions.stream()
                .map(Enum::name)
                .toList();

        return new ApiResponse(HttpStatus.OK.value(), "retrieved Permisions", permissionNames);
    }


    @PutMapping("/editSecretary")
    public ApiResponse<SecretaireDTO> updateSecretaire(@RequestBody EditSecretaireDTO DTO, Authentication aut) {

        System.out.println("received dto" + DTO);
        UserDTO user = authService.getUserDto(aut);

        //* Valider le preseence du id  */
        Cabinet cabinet = cabinetAppService.getCabinetByEmail(user) ;
        int idCabinet =  cabinet.getIdCabinet() ;

        SecretaireDTO secretaire = medecinAppService.updateSecretaire(DTO, user);
        String message = "Secretary: " + secretaire.getIdSecretaire() + "updated for : " + idCabinet;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, secretaire);
    }
    @DeleteMapping("/deleteSecretary/{idsecretaire}")
    public ApiResponse<SecretaireDTO> deleteSecretaire(@PathVariable int idsecretaire) {
        System.out.println("received id:  " + idsecretaire);
        medecinAppService.deleteSecretaire(idsecretaire);
        String message = "deleted secretary: " + idsecretaire;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, null);
    }

    @GetMapping("/rendezvous/{idCabinet}")
    public ApiResponse<List<RendezVousDTO>> getAllRendezVous(@PathVariable int idCabinet) {
        List<RendezVousDTO> data = medecinAppService.getAllRendezVous(idCabinet);
        String message = "Appointments retrieved by" + idCabinet;
        int status = HttpStatus.OK.value();
        return new ApiResponse<>(status, message, data);
    }

    @PutMapping("/editRendezVous")
    public ApiResponse<RendezVousDTO> updateRendezVous(@RequestBody RendezVousDTO rendezVousDTO) {

        RendezVousDTO rv = medecinAppService.editRendezVous(rendezVousDTO);
        String message = "Appointment: "+ rendezVousDTO.getIdRendezVous() + " updated for: " + rendezVousDTO.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, rv);
    }

    @GetMapping("/getStats/{idCabinet}")
    public ApiResponse<StatsDTO>  getStats(
        @PathVariable int idCabinet ,
        Authentication aut 
    ) {

        UserDTO user  = authService.getUserDto(aut) ;
        StatsDTO stats = medecinAppService.getStats(user);
        String message = "Stats retrieved by" + idCabinet;
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, stats);

    }
    @PostMapping("/addDepence")
    public ApiResponse<DepenceDTO> addDepence(@RequestBody DepenceDTO dto) {

        System.out.println("received dto" + dto);

        DepenceDTO depence = medecinAppService.addDepence(dto);
        String message = "Depence added for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, depence);

    }
    @DeleteMapping("/deleteDepence")
    public ApiResponse<DepenceDTO> deleteDepence(@RequestBody DepenceDTO dto) {
        System.out.println("received dto" + dto);

        medecinAppService.deleteDepence(dto);
        String message = "Depence deleted for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, null);

    }
    @PutMapping("/editDepence")
    public ApiResponse<DepenceDTO> editDepence(@RequestBody DepenceDTO dto) {

        System.out.println("Received Depence" + dto);

        DepenceDTO depence = medecinAppService.updateDepence(dto);
        String message = "Depence updated for" + dto.getIdCabinet();
        int status = HttpStatus.OK.value();

        return new ApiResponse<>(status, message, depence);
    }

}
