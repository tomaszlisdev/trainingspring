package com.pivovarit.movies.domain;

import java.util.Collection;
import java.util.Optional;

interface MovieRepository {
    MovieId save(Movie movie);

    Collection<Movie> findAll();
    Optional<Movie> findByTitle(String title);
    // TODO find all by id
    // TODO find all by type
    // TODO find all by year
    // TODO find all before year
    // TODO find all between years sorted descending
}
