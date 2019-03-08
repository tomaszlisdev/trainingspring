package com.pivovarit.movies.domain;

import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
class JpaMovieRepository implements MovieRepository {

    private final SpringDataMovieRepository crudMovieRepository;

    @Override
    public MovieId save(Movie movie) {
        crudMovieRepository
            .save(new HibernatePersistedMovie(movie.getId().getId(), movie.getTitle(), movie.getType().toString(), movie
                .getYear().get(ChronoField.YEAR)));

        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return StreamSupport.stream(crudMovieRepository.findAll().spliterator(), false)
            .map(m -> new Movie(new MovieId(m.getId()), m.getTitle(), MovieType.valueOf(m.getType()), Year.of(m.getYear())))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return crudMovieRepository.findByTitle(title)
            .map(m -> new Movie(new MovieId(m.getId()), m.getTitle(), MovieType.valueOf(m.getType()), Year.of(m.getYear())));
    }
}
