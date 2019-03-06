package com.pivovarit.movies;

import com.pivovarit.movies.domain.MovieFacade;
import com.pivovarit.movies.dto.MovieDto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRESTResource{
	
	
    private MovieFacade movieFacade;
   
    @Autowired
    MovieRESTResource( MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
        System.out.println("aaaaa");
    }

    @GetMapping("/movies")
    Collection<MovieDto> getFilms() {
        return movieFacade.findAll();
    }
    
    
}