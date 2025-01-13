package com.example.movies_searcher.controller;

import com.example.movies_searcher.model.BuscarRequest;
import com.example.movies_searcher.model.Movies;
import com.example.movies_searcher.service.IMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class MovieController {
    @Autowired
    private IMoviesService moviesService;

    @GetMapping("/get")
    public List<Movies> getAllMovies() {
        List<Movies> movies = moviesService.listarPeliculas();
       // movies.forEach(movie -> System.out.println(movie.toString()));
        return movies;
    }


    @PostMapping("/buscar")
    public ResponseEntity<String> getMovieByName(@RequestBody BuscarRequest buscarRequest) {

        try{
            List<Movies> resultados = moviesService.buscarporNombre(buscarRequest.getBuscar());
            Optional<Movies> recomendacion = moviesService.obtenerPeliculaAleatoria();

            if(resultados.isEmpty()){
                Optional<Movies> peliRecomendada = moviesService.obtenerPeliculaAleatoria();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliRecomendada.get().toString());
            }
            return ResponseEntity.ok(resultados.toString());

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error:" + e.getMessage());
        }

    }


    /*
    @PostMapping("/buscar")
    public List<Movies> getMovieByName(@RequestBody BuscarRequest buscarRequest) {
        List<Movies> resultados = moviesService.buscarporNombre(buscarRequest.getBuscar());
        return resultados;
    }
*/

    @PostMapping("/buscar_estudio")
    public List<Movies> getMovieByStudio(@RequestBody BuscarRequest buscarRequest) {
        List<Movies> resultados = moviesService.filtrarporStudio(buscarRequest.getBuscar());
        return resultados;
    }

    @PostMapping("/post_movie")
    public Movies postMovies(@RequestBody Movies movies){
        return moviesService.saveorUpdateMovie(movies);
    }

    @DeleteMapping("/{MovieId}")
    public Movies deleteMovie(@PathVariable("MovieId") Integer id){
        return moviesService.deleteMovie(id);
    }


}