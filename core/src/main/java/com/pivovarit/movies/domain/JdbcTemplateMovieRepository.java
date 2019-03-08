package com.pivovarit.movies.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
class JdbcTemplateMovieRepository implements MovieRepository {

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private final JdbcTemplate jdbcTemplate;

    @Override
    public MovieId save(Movie movie) {

        jdbcTemplate.update("INSERT INTO movie VALUES (?, ?, ?, ?)",
            movie.getId().getId(),
            movie.getTitle(),
            movie.getType().toString(),
            movie.getYear().get(ChronoField.YEAR));

        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movie", new MovieRowMapper());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return jdbcTemplate.query(
            "SELECT * FROM movie WHERE title = ?",
            new Object[]{title}, MOVIE_ROW_MAPPER)
            .stream()
            .findAny();
    }

    static class MovieRowMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Movie(
                new MovieId(resultSet.getString("id")),
                resultSet.getString("title"),
                MovieType.valueOf(resultSet.getString("type")),
                Year.of(resultSet.getInt("year")));
        }
    }
}
