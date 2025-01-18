# Aplicación en Java para Consumir la API de Gutendex

Esta aplicación en Java permite interactuar con la [API de Gutendex](https://gutendex.com/), obtener información sobre libros y almacenarlos en una base de datos para realizar consultas de manera eficiente. Los usuarios pueden buscar libros por título, ver libros y autores registrados, y realizar consultas basadas en idioma o año.

---

## Funcionalidades

- **Buscar libros por título**: Obtén detalles de libros desde la API de Gutendex y guárdalos en la base de datos.
- **Listar libros registrados**: Visualiza todos los libros almacenados en la base de datos.
- **Listar autores registrados**: Visualiza todos los autores almacenados en la base de datos.
- **Consultar autores vivos en un año específico**: Busca autores según su periodo de vida y un año dado.
- **Filtrar libros por idioma**: Recupera libros de la base de datos según su idioma.

---

## Requisitos

Para ejecutar la aplicación, asegúrate de tener instalado lo siguiente:
- Java 17 o superior.
- Maven.
- Una base de datos (p. ej., PostgreSQL, MySQL o H2 para desarrollo).
- Configuración adecuada para la conexión a la base de datos en el archivo `application.properties`.

---

## Instrucciones de Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/gutendex-java-app.git
cd gutendex-java-app
```

### 2. Configurar la Base de Datos
Edita el archivo application.properties en el directorio src/main/resources para que coincida con tu configuración de base de datos:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
### 3. Construir el Proyecto
Usa Maven para construir el proyecto:
```bash
mvn clean install
```
### 4. Ejecutar la Aplicación
Ejecuta la aplicación con el siguiente comando:
```
mvn spring-boot:run
```


## Cómo Usar
Opciones del Menú:
1. Buscar libro por título: Ingresa el título del libro para obtener los detalles desde la API de Gutendex y guardarlos en la base de datos.
2. Listar libros registrados: Muestra todos los libros almacenados en la base de datos.
3. Listar autores registrados: Muestra todos los autores almacenados en la base de datos.
4. Listar autores vivos en un año específico: Ingresa un año para ver los autores que estaban vivos en ese periodo.
5. Listar libros por idioma: Ingresa un código de idioma (p. ej., es, en) para ver los libros en ese idioma.
0. Salir: Cierra la aplicación.
