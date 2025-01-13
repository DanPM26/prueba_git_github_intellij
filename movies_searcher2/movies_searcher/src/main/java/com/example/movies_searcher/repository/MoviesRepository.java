package com.example.movies_searcher.repository;

import com.example.movies_searcher.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//JpaRepository gestionará los datos bajo métodos de un CRUD
public interface MoviesRepository extends JpaRepository<Movies,Integer> {

   /* @Query("SELECT m FROM movies WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :film, '%'))")
    List<Movies> findByNameContaining(@Param("film") String film);*/
    /*
    @Query(value= "SELECT m" + "FROM movies m" + "WHERE film = :film" )
    List<Movies> findByTitle(@Param("film") String film);
     */
    List<Movies> findByFilm(String nombre);

    //Movies deleteAllById(Integer id);


    //   List<Movies> findByStudio(Studio studio);

}
