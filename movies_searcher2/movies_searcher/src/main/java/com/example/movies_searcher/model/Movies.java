package com.example.movies_searcher.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Db_prueba")
// Se generan los métodos GET y SET
@Data

// Agregar constructor vacio a nuestra clase
@NoArgsConstructor

// Constructor con todos los argumentos
@AllArgsConstructor

@ToString

@EqualsAndHashCode

public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MOVIE_ID;
    private String NOMBRE_MOVIE;
    private String DIRECTOR;
    private Integer MOVIE_EXPENSE;
    private Integer RECOVERED_EXPENSE;
    private Integer CATEGORY_ID;

/*
    public String getLeadStudio(){
        return lead_studio;
    }
 */
/*
    @Column(name = "lead_studio")
    private String leadStudio;
 */
}
