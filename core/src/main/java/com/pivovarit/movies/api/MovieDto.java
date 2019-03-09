package com.pivovarit.movies.api;

import lombok.Value;

@Value
public class MovieDto {
    private final String id;
    private final String title;
    private final String details;
    private final MovieTypeDto type;
    private final Integer year;
}
