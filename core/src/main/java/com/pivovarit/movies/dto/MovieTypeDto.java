package com.pivovarit.movies.dto;

public class MovieTypeDto {
    private final String movieType;

    public MovieTypeDto(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieType() {
        return movieType;
    }
}
