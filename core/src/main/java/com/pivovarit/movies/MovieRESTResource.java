package com.pivovarit.movies;

import com.pivovarit.movies.domain.MovieFacade;
import com.pivovarit.movies.dto.MovieDto;

import java.util.Collection;

public class MovieRESTResource {
    private MovieFacade movieFacade;

    MovieRESTResource(MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
    }

    Collection<MovieDto> getFilms() {
        return movieFacade.findAll();
    }
}