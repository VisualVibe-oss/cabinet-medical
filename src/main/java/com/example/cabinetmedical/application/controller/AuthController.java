package com.example.cabinetmedical.application.controller;

import com.example.cabinetmedical.application.ResponseApi.ApiResponse;
import com.example.cabinetmedical.application.DTO.LoginDTO;
import com.example.cabinetmedical.application.DTO.MedecinDTO;
import com.example.cabinetmedical.application.DTO.SignupDataDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.application.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private  AuthService authService;
    @Autowired
    private JwtService jwtService;

    private void setHeaders(String accessToken, String refreshToken, HttpServletResponse response) {

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 15)
                .sameSite("Strict")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/api/auth/refresh")
                .maxAge(60 * 60 * 24 * 7)
                .sameSite("Strict")
                .build();

        response.setHeader("Access-Token", accessCookie.toString());
        response.setHeader("Refresh-Token", refreshCookie.toString());
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDTO>> signUp(@RequestBody Map<String,Object > object, HttpServletResponse response) {

        ObjectMapper mapper = new ObjectMapper();
       SignupDataDTO signupData = mapper.convertValue(object, SignupDataDTO.class);
        authService.signup(signupData);

        MedecinDTO medecin = signupData.getMedecin();

        String accessToken = jwtService.generateAccesToken(medecin);
        String refreshToken = jwtService.generateAndSaveRefreshToken(medecin);

        UserDTO user = new UserDTO();
        user.setEmail(medecin.getEmail().toUpperCase());
        user.setRole("MEDECIN");
        setHeaders(accessToken, refreshToken, response);
        ApiResponse<UserDTO> apiResponse = ApiResponse.<UserDTO>builder()
                .data(user)
                .message("User registered successfully")
                .status(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> signUp(@RequestBody LoginDTO loginData, HttpServletResponse res ) {

        UserDTO user = authService.login(loginData) ;

        String accessToken = jwtService.generateAccesToken(user);
        String refreshToken = jwtService.generateAndSaveRefreshToken(user);
        setHeaders(accessToken, refreshToken, res);
        ApiResponse<UserDTO> apiResponse = ApiResponse.<UserDTO>builder()
                .data(user)
                .message("User registered successfully")
                .status(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.ok(apiResponse);

    }

    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null)
            return null;

        for (Cookie cookie : request.getCookies()) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refreshToken(@RequestBody HttpServletRequest request , HttpServletResponse response) {

        String token = getRefreshTokenFromCookies(request) ;
        String accesToken = jwtService.refreshToken(token);
        setHeaders(accesToken, token, response);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data("Acces token acctualise avec succes")
                .message("succes")
                .status(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.ok(apiResponse) ;
    }






    @PostMapping("/deconnexion")
    public ResponseEntity<String> deconnecter(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            return ResponseEntity.ok("Déconnexion réussie");
        }
        return ResponseEntity.badRequest().body("Aucun utilisateur connecté");
    }
}
