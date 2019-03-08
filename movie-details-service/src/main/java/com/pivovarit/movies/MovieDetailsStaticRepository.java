package com.pivovarit.movies;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class MovieDetailsStaticRepository implements MovieRepository {

    private final Map<String, String> movieDetails = new HashMap<>();

    public MovieDetailsStaticRepository() {
        movieDetails.put("idkfa78", "When bitten by a genetically modified spider, a nerdy, shy, and awkward high school student gains spider-like abilities that he eventually must use to fight evil as a superhero after tragedy befalls his family.");
        movieDetails.put("asdf195", "A team of commandos on a mission in a Central American jungle find themselves hunted by an extraterrestrial warrior.");
    }

    @Override
    public String getDetails(String title) {
        return null;
    }
}
