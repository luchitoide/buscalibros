package com.curso.buscalibros.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nacimiento;
    private String muerte;
    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){

    }

    public Autor(DatosAutor datosAutor) {
        this.nombre=datosAutor.nombre();
        this.muerte= datosAutor.muerte();
        this.nacimiento= datosAutor.nacimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        this.muerte = muerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "\n---------------------" +
                "\nNombre: " + nombre +
                "\nNacimiento: " + nacimiento +
                "\nMuerte: " + muerte +
                "\nLibros escritos: " + libros +
                "\n---------------------";
    }
}
