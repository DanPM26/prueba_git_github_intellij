package com.example.movies_searcher.service;

import com.example.movies_searcher.model.Movies;
import com.example.movies_searcher.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service

public class MoviesService implements IMoviesService {
    // se inyectan las dependencias, ya sea un constructor o un metodo

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public List<Movies> listarPeliculas() {
        List<Movies> movies = moviesRepository.findAll();
        return movies;
    }

     @Override
    public Movies buscarporId(Integer idMovie){
       Movies movies = moviesRepository.findById(idMovie).orElse(null);
        return movies;
     }

    /*
    @Override
    public List<Movies> buscarPeliculaporNombre(String film) {
     List<Movies> movies = moviesRepository.findByNameContaining(film);
     return movies;
    }
    */

    @Override
    public List<Movies> buscarporNombre(String nombre){

        return moviesRepository.findByFilm(nombre);
      }

    @Override
    public Optional<Movies> obtenerPeliculaAleatoria() {
        List<Movies> todaspeliculas = moviesRepository.findAll();
        if(todaspeliculas.isEmpty()){
            return Optional.empty();
        }
        Random random = new Random();
        return Optional.of(todaspeliculas.get(random.nextInt(todaspeliculas.size())));
    }

    @Override
    public Movies saveorUpdateMovie(Movies movies) {

        return moviesRepository.save(movies);
    }

    @Override
     public List<Movies> filtrarporStudio( String leadStudio){
        List<Movies> movies= moviesRepository.findAll();
        List<Movies> resultados = new ArrayList<>();
        for (Movies e: movies){
            if(e.getNOMBRE_MOVIE().equalsIgnoreCase(leadStudio)){
                resultados.add(e);
            }
        }
        return resultados;
     }

    @Override
    public Movies deleteMovie(Integer id) {
       moviesRepository.deleteById(id);
        return null;
    }

/*
    @Override
    public List<Movies> buscarporProveedor(String proveedorStudio) {
        List<Movies> proveedor = moviesRepository.findByStudioName(proveedorStudio);
        return proveedor;
       // return  moviesRepository.findByProveedor(proveedorFilm);
    }
*/
      /*
    @Override
    public List<Movies> buscarporProveedor(String proveedorFilm) {
        return moviesRepository.findByProveedor(proveedorFilm);
    }
    */


   /*
      @Override
       public List<Movies> buscarporProveedor(String proveedorFilm){
        return moviesRepository.findByProveedor(proveedorFilm);
      }
    */

}

