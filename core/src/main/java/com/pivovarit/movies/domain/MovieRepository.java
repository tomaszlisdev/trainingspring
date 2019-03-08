package com.pivovarit.movies.domain;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();
    Optional<Movie> findByTitle(String title);
    Stream<Movie> findAllById(MovieId id);
    Stream<Movie> findAllByType(MovieType id);
    Stream<Movie> findAllByTypeAndYearBefore(MovieType type, Year year);
    Stream<Movie> findAllByYearBetween(Year first, Year second);
    Stream<Movie> findAllByYearOrderByYearAsc(Year year);
}
