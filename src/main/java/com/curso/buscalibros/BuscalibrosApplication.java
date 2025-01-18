package com.curso.buscalibros;

import com.curso.buscalibros.principal.Principal;
import com.curso.buscalibros.repository.AutorRepository;
import com.curso.buscalibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscalibrosApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BuscalibrosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository,libroRepository);
		principal.muestraMenu();
	}
}
