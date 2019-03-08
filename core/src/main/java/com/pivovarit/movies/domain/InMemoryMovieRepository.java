package com.pivovarit.movies.domain;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

class InMemoryMovieRepository implements MovieRepository {

    private final Map<String, Movie> storage = new ConcurrentHashMap<>();

    @Override
    public MovieId save(Movie movie) {
        requireNonNull(movie);
        storage.put(movie.getId().getId(), movie);
        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return storage.values().stream()
            .filter(m -> m.getTitle().equals(title))
            .findAny();
    }

    @Override
    public Stream<Movie> findAllById(MovieId id) {
        return storage.values().stream()
            .filter(m -> m.getId().getId().equals(id.getId()));
    }

    @Override
    public Stream<Movie> findAllByType(MovieType id) {
        return null; // TODO
    }

    @Override
    public Stream<Movie> findAllByTypeAndYearBefore(MovieType type, Year year) {
        return null; // TODO
    }

    @Override
    public Stream<Movie> findAllByYearBetween(Year first, Year second) {
        return null; // TODO
    }

    @Override
    public Stream<Movie> findAllByYearOrderByYearAsc(Year year) {
        return null; // TODO
    }
}
