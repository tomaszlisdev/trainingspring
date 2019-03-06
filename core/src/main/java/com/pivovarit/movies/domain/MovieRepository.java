package com.pivovarit.movies.domain;

import java.util.Collection;
import java.util.Optional;

public interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();
    Optional<Movie> findByTitle(String title);
}
