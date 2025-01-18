package com.curso.buscalibros.repository;

import com.curso.buscalibros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE %:nombre%")
    Optional<Autor> autorPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN CAST(a.nacimiento AS integer) AND CAST(a.muerte AS integer)")
    List<Autor> autoresVivos(@Param("ano") int ano);
}
