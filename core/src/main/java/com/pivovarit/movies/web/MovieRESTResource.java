package com.pivovarit.movies.web;

import com.pivovarit.movies.domain.MovieFacade;
import com.pivovarit.movies.api.MovieDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

// check http://localhost:8080/swagger-ui.html
@RequiredArgsConstructor
@RestController
@Lazy
public class MovieRESTResource {
    private final MovieFacade movieFacade;

    @ApiOperation(value = "Fetch all movies", nickname = "Fetch movies")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = MovieDto.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    @GetMapping("/movies")
    public Collection<MovieDto> getFilms() {
        return movieFacade.findAll();
    }

    @ApiOperation(value = "Fetch a single movie with its details", nickname = "Fetch details")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = MovieDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")}) // Swagger annotation
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Movie's id", required = true, dataType = "string", paramType = "path")
    })
    @GetMapping("/movies/{id}")
    public Optional<MovieDto> getFilm(@PathVariable String id) {
        return movieFacade.findById(id);
    }
}