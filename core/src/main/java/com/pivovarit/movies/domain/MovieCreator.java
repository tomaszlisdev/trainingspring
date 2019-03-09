package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;

import java.time.Year;

class MovieCreator {
    Movie from(MovieDto filmDto) {
        return new Movie(new MovieId(filmDto.getId()), filmDto.getTitle(), MovieType
            .valueOf(filmDto.getType().getMovieType()), Year.of(filmDto.getYear()));
    }

    Movie from(HibernatePersistedMovie filmDto) {
        return new Movie(MovieId.of(filmDto.getId()), filmDto.getTitle(), MovieType.valueOf(filmDto.getType()), Year
            .of(filmDto.getYear()));
    }
}
