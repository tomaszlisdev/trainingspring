package com.pivovarit.movies.dto;

public class MovieDto {
    private final Long id;
    private final String title;
    private final MovieTypeDto type;

    public MovieDto(Long id, String title, MovieTypeDto type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public MovieTypeDto getType() {
        return type;
    }
}
