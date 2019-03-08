package com.pivovarit.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SpringDataMovieRepository extends CrudRepository<HibernatePersistedMovie, String> {
    Optional<HibernatePersistedMovie> findByTitle(String title);
}

