package com.pivovarit.movies.domain;

import lombok.RequiredArgsConstructor;

import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
class JpaMovieRepository implements MovieRepository {

    private final SpringDataMovieRepository crudMovieRepository;
    private final MovieCreator movieCreator;

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
            .map(m -> new Movie(new MovieId(m.getId()), m.getTitle(), MovieType.valueOf(m.getType()), Year
                .of(m.getYear())))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return crudMovieRepository.findByTitle(title)
            .map(movieCreator::from);
    }

    @Override
    public Stream<Movie> findAllById(MovieId id) {
        return crudMovieRepository.findAllById(id.getId())
            .map(movieCreator::from);
    }

    @Override
    public Stream<Movie> findAllByType(MovieType id) {
        return crudMovieRepository.findAllByType(id.toString())
            .map(movieCreator::from);
    }

    @Override
    public Stream<Movie> findAllByTypeAndYearBefore(MovieType type, Year year) {
        return crudMovieRepository.findAllByTypeAndYearBefore(type.toString(), year.get(ChronoField.YEAR))
            .map(movieCreator::from);
    }

    @Override
    public Stream<Movie> findAllByYearBetween(Year first, Year second) {
        return crudMovieRepository.findAllByYearBetween(first.get(ChronoField.YEAR), second.get(ChronoField.YEAR))
            .map(movieCreator::from);
    }

    @Override
    public Stream<Movie> findAllByYearOrderByYearAsc(Year year) {
        return crudMovieRepository.findAllByYearOrderByYearAsc(year.get(ChronoField.YEAR))
            .map(movieCreator::from);
    }
}
