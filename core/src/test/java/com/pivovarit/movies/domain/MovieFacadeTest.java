package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieFacadeTest {

    private MovieFacade movieFacade;

    @Before
    public void setup() {
        movieFacade = MovieFacade.inMemoryMovieFacade();
    }

    @Test
    public void shouldAddMovie() {
        // given
        String title = "foo";
        MovieDto dto = new MovieDto("spdrmun1", title, "", new MovieTypeDto("NEW"), 2012);

        // when
        movieFacade.add(dto);

        // then
        MovieDto result = movieFacade.findByTitle(title);
        assertThat(result).isEqualTo(dto);
    }
}
