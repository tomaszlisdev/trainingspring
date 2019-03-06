package com.pivovarit.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class ApplicationInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Started!");
    }
}
