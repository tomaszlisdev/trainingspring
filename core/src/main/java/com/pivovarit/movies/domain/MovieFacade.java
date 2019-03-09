package com.pivovarit.movies.domain;

import com.pivovarit.movies.infrastructure.MovieDetailsClient;
import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MovieFacade {

    private final MovieRepository filmRepository;
    private final MovieCreator movieCreator;
    private final MoviePriceCalculator moviePriceCalculator;
    private final MovieDetailsClient movieDetailsClient;

    public static MovieFacade inMemoryMovieFacade() {
        return new MovieFacade(new InMemoryMovieRepository(), new MovieCreator(), new StaticMoviePriceCalculator(42, 42, 42), f -> new MovieDetailsClient.MovieDetails(UUID
            .randomUUID().toString()));
    }

    public MovieId add(MovieDto filmDto) {
        return filmRepository.save(movieCreator.from(filmDto));
    }

    public Optional<Long> priceFor(MovieTypeDto type) {
        try {
            return Optional.of(moviePriceCalculator.getPrice(MovieType.valueOf(type.getMovieType())));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<MovieDto> findById(String movieId) {
        return filmRepository.findAllById(MovieId.of(movieId))
            .findAny()
            .map(toMovieDto());
    }

    private Function<Movie, MovieDto> toMovieDto() {
        return movie -> {
            MovieDetailsClient.MovieDetails movieDetails = movieDetailsClient.get(movie.getId().getId());

            return new MovieDto(
                movie.getId().getId(),
                movie.getTitle(),
                movieDetails.getDetails(),
                new MovieTypeDto(movie.getType().toString()),
                movie.getYear().get(ChronoField.YEAR));
        };
    }

    public MovieDto findByTitle(String movieTitle) {
        Movie movie = filmRepository.findByTitle(movieTitle).orElseThrow(IllegalStateException::new);
        return toMovieDto().apply(movie);
    }

    public List<MovieDto> findAll() {
        return filmRepository.findAll().stream()
            .map(toMovieDto())
            .collect(Collectors.toList());
    }
}
