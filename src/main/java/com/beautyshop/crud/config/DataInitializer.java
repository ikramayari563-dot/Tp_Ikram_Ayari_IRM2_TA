package com.beautyshop.crud.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("✅ Application démarrée avec succès. La base de données est vide et prête à recevoir des données via l'API.");
    }
}