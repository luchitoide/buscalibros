package com.curso.buscalibros.repository;

import com.curso.buscalibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT COUNT(b) > 0 FROM Libro b WHERE b.titulo LIKE %:titulo%")
    Boolean verifiedBDExistence(@Param("titulo") String titulo);

    @Query(value = "SELECT * FROM libros WHERE :idiomaABuscar = ANY (libros.idioma)", nativeQuery = true)
    List<Libro> librosPorIdioma(@Param("idiomaABuscar") String idiomaABuscar);

}
