package com.pivovarit.movies;

import com.pivovarit.movies.domain.MovieFacade;
import com.pivovarit.movies.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Lazy
public class MovieRESTResource {
    private final MovieFacade movieFacade;

    @GetMapping("/movies")
    public Collection<MovieDto> getFilms() {
        return movieFacade.findAll();
    }

    @GetMapping("/movies/{id}")
    public Optional<MovieDto> getFilm(@PathVariable String id) {
        return movieFacade.findById(id);
    }
}