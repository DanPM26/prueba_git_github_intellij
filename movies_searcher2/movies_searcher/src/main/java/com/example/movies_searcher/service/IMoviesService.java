package com.example.movies_searcher.service;

import com.example.movies_searcher.model.Movies;

import java.util.List;
import java.util.Optional;

// Interfaz de Servicio

public interface IMoviesService {
    public List<Movies> listarPeliculas();

    public Movies buscarporId(Integer idMovie);

    public List<Movies> buscarporNombre(String nombre);

    Optional<Movies> obtenerPeliculaAleatoria();


   public Movies saveorUpdateMovie(Movies movies);

    List<Movies> filtrarporStudio(String leadStudio);

    public Movies deleteMovie(Integer id);
}
