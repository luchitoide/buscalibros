package com.curso.buscalibros.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;
    private List<String> idioma = new ArrayList<>();
    private double numeroDeDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autor = new Autor(datos.autor().getFirst());
        this.idioma = datos.idiomas();
        this.numeroDeDescargas = datos.numeroDeDescargas();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "\n---------------------" +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nDescargas: " + numeroDeDescargas +
                "\n---------------------";
    }
}
