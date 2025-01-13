package com.example.movies_searcher;

import com.example.movies_searcher.model.Movies;
import com.example.movies_searcher.service.IMoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class MoviesSearcherApplication implements CommandLineRunner {

	@Autowired
	private IMoviesService moviesService;

	//Se utiliza para registrar la información sobre la ejecución del programa
	private static final Logger logger = LoggerFactory.getLogger(MoviesSearcherApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		SpringApplication.run(MoviesSearcherApplication.class, args);
		logger.info("Aplicacion terminada");

	}

	@Override
	public void run(String... args) throws Exception {
		moviesApp();
	}

	// aplicacion peliculas
	private void moviesApp(){
		logger.info(nl +"***Aplicación buscador de películas***" + nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
				1. Listar películas disponibles
				2.- Buscar pelicula 
				3.- Buscar pelicula por proveedor
				4.- Salir
				Elige una opción:\s
				""");
		var opcion = Integer.parseInt(consola.nextLine());
        return opcion;

    }

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info(nl + "------Listado de peliculas disponibles-----" + nl);
				List<Movies> movies = moviesService.listarPeliculas();
				movies.forEach(movie -> logger.info(movie.toString()));
			}
			case 2 -> {
				logger.info(nl + "------Buscar pelicula-----" + nl);
				logger.info("Buscar:");
				/*
				var buscarid = Integer.parseInt(consola.nextLine());
				Movies movies= moviesService.buscarporId(buscarid);
				if(movies != null){
					logger.info("Pelicula encontrada:" + movies);
				} else {
					logger.info("Lo sentimos no encontramos la película pero tal vez te gusten:");
				}
               */
				var buscar = consola.nextLine();
              List<Movies> resultados = moviesService.buscarporNombre(buscar);

			  /*
			  if (movies.isEmpty()){
				  logger.info("No se encontraron peliculas");
			  } else {
				  logger.info("Pelicula encontrada:");
				  movies.forEach(pelicula ->logger.info(pelicula.getFilm()));
			  }
             */
				Optional<Movies> recomendacion = moviesService.obtenerPeliculaAleatoria();
				if (resultados.isEmpty()){
					logger.info("Disculpe, no se encontró la película que busca pero tal vez quiera ver alguna de nuestras recomendaciones:");
					Movies peliRecomendada = recomendacion.get();

					logger.info("Titulo:" + peliRecomendada.getNOMBRE_MOVIE());

				} else {
					logger.info("Pelicula encontrada:");
					for(Movies e: resultados){
							logger.info("Titulo:" + e.getNOMBRE_MOVIE());
							logger.info("Director:" + e.getDIRECTOR());
							logger.info("Gasto:" + e.getMOVIE_EXPENSE());
							logger.info("Recuperación:"+ e.getRECOVERED_EXPENSE());

					}
				}

			}
			case 3 ->{
				logger.info(nl + "------Busqueda por proveedor-----" + nl);
				logger.info("Buscar:");
				var studio = consola.nextLine();
				List<Movies> proveedor = moviesService.filtrarporStudio(studio);
				Optional<Movies> recomendacion = moviesService.obtenerPeliculaAleatoria();
				if(proveedor.isEmpty()){
					logger.info("No se encontraron peliculas relacionadas a ese estudio pero tal vez te guste:");
					Movies peliRecomendada = recomendacion.get();

					logger.info("Titulo:" + peliRecomendada.getNOMBRE_MOVIE());
					logger.info("Director:" + peliRecomendada.getDIRECTOR());
					logger.info("Gasto:" + peliRecomendada.getMOVIE_EXPENSE());
					logger.info("Recuperación:"+ peliRecomendada.getRECOVERED_EXPENSE());

				} else{
					logger.info("Peliculas encontradas:");
					for(Movies e: proveedor){
						logger.info("--------------------");
						logger.info("Titulo:" + e.getNOMBRE_MOVIE());
						logger.info("Director:" + e.getDIRECTOR());
						logger.info("Gasto:" + e.getMOVIE_EXPENSE());
						logger.info("Recuperación:"+ e.getRECOVERED_EXPENSE());
						logger.info("--------------------");
					}
				}

			}
			case 4 -> {
				salir = true;
				logger.info("Gracias, vuelva pronto (:");
			}
		}
		return salir;
	}
}
