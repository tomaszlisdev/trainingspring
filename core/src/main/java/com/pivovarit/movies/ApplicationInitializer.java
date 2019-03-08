package com.pivovarit.movies;

import com.pivovarit.movies.domain.MovieFacade;
import com.pivovarit.movies.dto.MovieDto;
import com.pivovarit.movies.dto.MovieTypeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;

@Component
@RequiredArgsConstructor
@Slf4j
class ApplicationInitializer implements CommandLineRunner {

    private final MovieFacade movieFacade;

    @Override
    public void run(String... args) {
        System.out.println("Started!");

        try {
            movieFacade.add(new MovieDto("spdrmun1", "Spiderman1", new MovieTypeDto("NEW"), 2012));
        } catch (Exception e) {
            log.warn("duplicates ignored", e);
        }
        movieFacade.priceFor(new MovieTypeDto("NEW"))
            .ifPresent(System.out::println);
    }
}
