package com.pivovarit.movies.domain;

import lombok.Value;

import java.time.Year;

@Value
class Movie {
    private final MovieId id;
    private final String title;
    private final MovieType type;
    private final Year year;
}
