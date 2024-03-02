El proyecto  fue desarrolado con el lenguaje de Java y desarrollado con el framework Spring Boot. El archivo pom.xml contiene la configuración de las dependencias del proyecto, incluyendo las bibliotecas de Spring Boot, H2 Database, Lombok, y otras más que son necesarias para el funcionamiento del proyecto.
Dpendencia y librerias 
spring-boot-starter-data-jpa: Este starter incluye las dependencias necesarias para utilizar Spring Data JPA, que simplifica el acceso a bases de datos relacionales a través de ORM (Object-Relational Mapping). Esto permite trabajar con entidades Java en lugar de consultas SQL directas.
spring-boot-starter-web: Incluye las dependencias necesarias para desarrollar aplicaciones web con Spring MVC (Model-View-Controller), que es un framework para construir aplicaciones web y API RESTful.
spring-boot-starter-web-services: Proporciona las dependencias para desarrollar servicios web SOAP utilizando Spring Web Services. Estos servicios web utilizan el protocolo SOAP (Simple Object Access Protocol) para la comunicación entre aplicaciones.
com.h2database:h2: H2 es una base de datos en memoria muy popular para desarrollo y pruebas. Esta dependencia permite usar H2 como base de datos embebida en la aplicación Spring Boot.
org.projectlombok:lombok: Lombok es una librería que ayuda a reducir la cantidad de código boilerplate en Java. Permite generar automáticamente getters, setters, constructores, y otros métodos comunes, mejorando así la legibilidad y mantenibilidad del código.
org.springframework.boot:spring-boot-starter-test: Este starter incluye las dependencias necesarias para escribir pruebas unitarias y de integración en aplicaciones Spring Boot, utilizando JUnit, Mockito y otras herramientas comunes de pruebas en Java.
io.springfox:springfox-swagger2: Swagger es una herramienta para documentar APIs RESTful de forma automática. Esta dependencia agrega soporte para Swagger 2 en la aplicación Spring Boot, permitiendo generar documentación de la API de forma automática a partir de las anotaciones en el código.
io.springfox:springfox-swagger-ui: Esta dependencia agrega la interfaz de usuario de Swagger UI a la aplicación Spring Boot, permitiendo visualizar y probar la API a través de un navegador web.
javax.servlet:javax.servlet-api: Esta dependencia proporciona las clases y interfaces necesarias para trabajar con el protocolo HTTP en Java, incluyendo la definición de servlets y filtros para aplicaciones web. Es utilizada por Spring Boot para manejar las peticiones HTTP en la aplicación web.

La base de datos fue desarrollas con H2 y la documentacion fue realizada con la dependencia Swagger que nos sirve para documentar las aplicaciones de forma atomatica.



