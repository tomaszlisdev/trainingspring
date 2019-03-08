package com.pivovarit.movies.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
interface SpringDataMovieRepository extends JpaRepository<HibernatePersistedMovie, String> {
    Optional<HibernatePersistedMovie> findByTitle(String title);
    Stream<HibernatePersistedMovie> findAllById(String id);
    Stream<HibernatePersistedMovie> findAllByType(String id);
    Stream<HibernatePersistedMovie> findAllByTypeAndYearBefore(String type, int year);
    Stream<HibernatePersistedMovie> findAllByYearBetween(int first, int second);
    Stream<HibernatePersistedMovie> findAllByYearOrderByYearAsc(int year);
}

