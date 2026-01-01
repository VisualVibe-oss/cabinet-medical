package com.example.cabinetmedical.config.springConfig;

import com.example.cabinetmedical.config.dev_sql.DataTestLoader;
import com.example.cabinetmedical.infrastructure.entity.*;
import com.example.cabinetmedical.infrastructure.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev") // Très important : s'exécute uniquement en mode DEV
@Order(2)
public class DevDataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CabinetRepository cabinetRepo,
            MedecinRepository medecinRepo,
            SecretaireRepository secretaireRepo,
            PasswordEncoder passwordEncoder,
            DataTestLoader dataLoader) {

        return args -> {
            System.out.println("--- Initialisation des données de développement ---");

            CabinetEntity cabinet = dataLoader.createCabinet();
            SecretaireEntity secretaire = dataLoader.createSecretaire(cabinet);

            PatientEntity patient1 = dataLoader.createPatient(cabinet, "Alami");
            PatientEntity patient2 = dataLoader.createPatient(cabinet, "Touzani");

            dataLoader.createRendezVous(cabinet, patient1);
            dataLoader.createRendezVous(cabinet, patient2);

            System.out.println("--- Données insérées avec succès ---");
        };
    }
}