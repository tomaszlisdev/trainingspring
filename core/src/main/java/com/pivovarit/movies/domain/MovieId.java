package com.pivovarit.movies.domain;

import lombok.Value;

@Value
class MovieId {
    private final String id;

    public static MovieId of(String id){
        return new MovieId(id);
    }
}
