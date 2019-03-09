package com.pivovarit.movies.domain;

import com.pivovarit.movies.infrastructure.HttpMovieDetailsClient;
import com.pivovarit.movies.infrastructure.MovieDetailsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

@Configuration
@Lazy
@Slf4j
class MoviesConfiguration {

    @Bean
    @Profile("in-mem")
    @Primary
    MovieFacade inMemoryMovieFacade() {
        return MovieFacade.inMemoryMovieFacade();
    }

    @Bean
    @Lazy // works only if bean not injected in CommandLineRunner instance
    MovieFacade movieFacade(
        MovieRepository movieRepository,
        MovieDetailsClient movieDetailsClient,
        @Value("${movies.price.new}") Long priceNew,
        @Value("${movies.price.old}") Long priceOld,
        @Value("${movies.price.regular}") Long priceRegular) {
        log.info("Initializing the movies facade");
        return new MovieFacade(movieRepository, new MovieCreator(), new StaticMoviePriceCalculator(priceNew, priceOld, priceRegular), movieDetailsClient);
    }

    @Bean
    MovieRepository inmemMovieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    @Primary
    MovieDetailsClient httpMovieDetailsClient() {
        return new HttpMovieDetailsClient();
    }

    @Bean
    MovieDetailsClient inmemMovieDetailsClient() {
        return a -> new MovieDetailsClient.MovieDetails(UUID.randomUUID().toString());
    }

    @Bean
    MovieRepository movieRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTemplateMovieRepository(jdbcTemplate);
    }

    @Bean
    @Profile("jpa")
    MovieRepository jpaMovieRepository(SpringDataMovieRepository springDataMovieRepository) {
        return new JpaMovieRepository(springDataMovieRepository, new MovieCreator());
    }
}
