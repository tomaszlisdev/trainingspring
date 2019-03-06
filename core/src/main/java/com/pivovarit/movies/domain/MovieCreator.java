package com.pivovarit.movies.domain;

import org.springframework.stereotype.Component;

import com.pivovarit.movies.dto.MovieDto;

@Component
public class MovieCreator {
    Movie from(MovieDto filmDto) {
        return new Movie(new MovieId(filmDto.getId()), filmDto.getTitle(), MovieType.valueOf(filmDto.getType().getMovieType()));
    }
}
