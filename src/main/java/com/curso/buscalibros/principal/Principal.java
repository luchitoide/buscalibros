package com.curso.buscalibros.principal;

import com.curso.buscalibros.model.*;
import com.curso.buscalibros.repository.AutorRepository;
import com.curso.buscalibros.repository.LibroRepository;
import com.curso.buscalibros.service.ConsumoApi;
import com.curso.buscalibros.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    private String nombreLibro;

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }


    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresPorAno();;
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }



    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+nombreLibro);
        if(!nombreLibro.isBlank()){

            //System.out.println(json);
            DatosJson datos = conversor.obtenerDatos(json, DatosJson.class);
            Optional<DatosLibro> librobuscado = datos.info().stream()
                    .filter(b -> b.titulo().toLowerCase().
                            contains(nombreLibro.toLowerCase()))
                    .findFirst();
            if (librobuscado.isPresent()){
                DatosLibro datoslibro = librobuscado.get();

                if(!existeLibro(datoslibro)){

                    Libro libro = new Libro(datoslibro);
                    DatosAutor datosAutor = datoslibro.autor().getFirst();
                    Optional<Autor> optionalAutor = autorRepository.autorPorNombre(datosAutor.nombre());
                    if(optionalAutor.isPresent()){
                        Autor actualAutor = optionalAutor.get();
                        libro.setAutor(actualAutor);
                        actualAutor.getLibros().add(libro);
                        autorRepository.save(actualAutor);
                    }else{
                        Autor nuevoAutor = new Autor(datosAutor);
                        libro.setAutor(nuevoAutor);
                        nuevoAutor.getLibros().add(libro);
                        autorRepository.save(nuevoAutor);
                    }
                    libroRepository.save(libro);
                }else{
                    System.out.println("libro ya existe en base de datos");
                }

                //System.out.println(libro.getTitulo());
                //System.out.println(libro.getAutor().getNombre());
            }else {
                System.out.println("libro no existe");
            }
        }else{
            System.out.println("ingrese titulo");
        }
    }

    private boolean existeLibro(DatosLibro datosLibro){
        Libro libro = new Libro(datosLibro);
        return libroRepository.verifiedBDExistence(libro.getTitulo());
    }

    private void mostrarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if(!libros.isEmpty()) {
            System.out.println("\n Lista de libros");
            libros.forEach(System.out::println);
        }else{
            System.out.println("No hay libros registrados");
        }
    }

    private void mostrarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if(!autores.isEmpty()) {
            System.out.println("\n Lista de autores");
            autores.forEach(System.out::println);
        }else{
            System.out.println("No hay autores registrados");
        }
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("Escribe el idioma del libro que deseas buscar");
        String idiomaLibro = teclado.nextLine();
        List<Libro> libros = libroRepository.librosPorIdioma(idiomaLibro);
        if(!libros.isEmpty()) {
            System.out.println("\n Lista de libros en el idioma: "+ idiomaLibro);
            libros.forEach(System.out::println);
        }else{
            System.out.println("No hay libros en ese idioma");
        }
    }

    private void mostrarAutoresPorAno() {
        System.out.println("Escribe el año que deseas buscar");
        int anoAutor = teclado.nextInt();
        List<Autor> autores = autorRepository.autoresVivos(anoAutor);
        if(!autores.isEmpty()) {
            System.out.println("\n Lista de autoress vivos en el año: "+ anoAutor);
            autores.forEach(System.out::println);
        }else{
            System.out.println("No hay autores en ese año");
        }
    }

}
