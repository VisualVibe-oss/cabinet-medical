package com.example.cabinetmedical.Controller;

import com.example.cabinetmedical.application.DTO.RendezVousDTO;
import com.example.cabinetmedical.application.DTO.UserDTO;
import com.example.cabinetmedical.application.service.AuthService;
import com.example.cabinetmedical.config.dev_sql.DataTestLoader;
import com.example.cabinetmedical.domain.utils.CreateRendezVousRequest;
import com.example.cabinetmedical.domain.utils.RendezVousState;
import com.example.cabinetmedical.infrastructure.entity.CabinetEntity;
import com.example.cabinetmedical.infrastructure.entity.MedecinEntity;
import com.example.cabinetmedical.infrastructure.entity.PatientEntity;
import com.example.cabinetmedical.infrastructure.entity.RendezVousEntity;
import com.example.cabinetmedical.infrastructure.entity.SecretaireEntity;
import com.example.cabinetmedical.infrastructure.mapper.RendezVousMapper;
import com.example.cabinetmedical.infrastructure.repository.RendezVous.SpringRendezVousRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Charge le contexte complet (Controller + Service + Repo)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test") // Utilise application-test.properties
public class RendezVousIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataTestLoader dataLoader;

    @Autowired
    private ObjectMapper objectMapper; // Injecté automatiquement par Spring


    @Autowired
    private SpringRendezVousRepository springRendezVousRepository;

    @BeforeEach
    @Transactional  
    void insertData() {
        // L'ordre est crucial pour les clés étrangères
        CabinetEntity cabinet = dataLoader.createCabinet();
        SecretaireEntity secretaire = dataLoader.createSecretaire(cabinet);

        PatientEntity patient1 = dataLoader.createPatient(cabinet, "Alami");
        PatientEntity patient2 = dataLoader.createPatient(cabinet, "Touzani");

        dataLoader.createRendezVous(cabinet, patient1);
        dataLoader.createRendezVous(cabinet, patient2);
    }

    @Test
    public void testGetRendezVous() throws Exception {
        // Préparation de l'objet
        UserDTO user = new UserDTO();
        user.setEmail("sanaa@email.com");
        user.setId(1);
        user.setRole(AuthService.secretaireRole);

        // Conversion en JSON et exécution
        MvcResult result = mockMvc.perform(post("/api/rendezvous/search") // Vérifiez si c'est bien POST ou GET dans
                                                                          // votre Controller
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))) // CORRECT : à l'intérieur de perform
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("Réponse reçue : " + jsonResponse);
    }
 // Pour récupérer un ID existant

    @Test
    public void testSetStateOngoing_Success() throws Exception {
        // 1. Récupérer un rendez-vous existant créé par le @BeforeEach
        RendezVousEntity rdvExistant = springRendezVousRepository.findAll().get(0);

        // 2. Préparer le UserDTO
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setEmail("sanaa@email.com");
        user.setRole(AuthService.secretaireRole);

        // 3. Préparer le RendezVousDTO avec l'ID du rendez-vous existant
        RendezVousDTO rdvDto = new RendezVousDTO();
        rdvDto.setIdRendezVous(rdvExistant.getIdRendezVous());
        rdvDto.setStatut(RendezVousState.ONGOING);
        
        // 4. Créer l'objet de requête (Wrapper)
        CreateRendezVousRequest request = new CreateRendezVousRequest();
        request.user = user;
        request.rendezVous = rdvDto;

        // 5. Exécution et Assertions
        mockMvc.perform(post("/api/secretaire/rendezVous/state") // Assurez-vous que le prefixe /api est présent si configuré
                                                  // globalement
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(true))
                .andExpect(jsonPath("$.message").value("La modification en succes"))
                .andExpect(jsonPath("$.status").value(201)); // Selon votre HttpStatus.CREATED.value()

        // 6. Vérification physique en base de données
        RendezVousEntity resultInDb = springRendezVousRepository.findById(rdvExistant.getIdRendezVous()).get();
        // Ici, on vérifie que le statut est bien passé à ONGOING (géré par votre
        // BehaviorPack)
        assertEquals(RendezVousState.ONGOING, resultInDb.getStatut());
    }

}