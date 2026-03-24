# Gestor de Proyectos — REST API

API REST para la gestión de proyectos de software, desarrollada con **Java 17** y **Spring Boot 2.5.8**. Permite administrar proyectos, equipos de trabajo (gerentes y desarrolladores), historias de usuario y tareas.

---

## Tabla de contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos previos](#requisitos-previos)
- [Cómo ejecutar el proyecto](#cómo-ejecutar-el-proyecto)
- [Endpoints de la API](#endpoints-de-la-api)
- [Guía de uso paso a paso](#guía-de-uso-paso-a-paso)
- [Documentación Swagger](#documentación-swagger)
- [Base de datos](#base-de-datos)
- [Tests](#tests)
- [Autor](#autor)

---

## Descripción

Este sistema permite gestionar proyectos de desarrollo de software siguiendo una estructura jerárquica:

```
Proyecto
  └── tiene un Gerente
  └── tiene varios Desarrolladores
  └── tiene Historias de Usuario
        └── tiene Tareas
```

Cada historia de usuario y cada tarea expone en su respuesta el **gerente** y los **programadores** asignados al proyecto, facilitando la trazabilidad del equipo a cargo.

---

## Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | 2.5.8 | Framework web |
| Spring Data JPA | 2.5.8 | Acceso a datos (ORM) |
| Hibernate | 5.4.x | Implementación JPA |
| H2 Database | 1.4.200 | Base de datos en memoria |
| Lombok | 1.18.30 | Reducción de boilerplate |
| Springfox Swagger | 2.9.2 | Documentación interactiva |
| Maven | 3.x | Gestión de dependencias |
| JUnit 5 + Mockito | — | Pruebas unitarias |

---

## Arquitectura

El proyecto sigue una **arquitectura de 3 capas** (N-Tier):

```
┌─────────────────────────────┐
│         Controller          │  ← Recibe peticiones HTTP (REST)
├─────────────────────────────┤
│          Service            │  ← Lógica de negocio
├─────────────────────────────┤
│         Repository          │  ← Acceso a datos (JPA)
├─────────────────────────────┤
│           Model             │  ← Entidades de la base de datos
└─────────────────────────────┘
```

---

## Estructura del proyecto

```
src/
└── main/
    └── java/com/brayanroncancio/gestionproyectos/
        ├── GestionproyectosApplication.java   # Punto de entrada
        ├── controller/
        │   └── Controller.java                # Endpoints REST
        ├── service/
        │   ├── UsuarioService.java
        │   ├── ProyectoService.java
        │   ├── HistoriaUsuarioService.java
        │   └── TareaService.java
        ├── repository/
        │   ├── UsuarioRepository.java
        │   ├── ProyectoRepository.java
        │   ├── HistoriaUsuarioRepository.java
        │   └── TareaRepository.java
        ├── model/
        │   ├── Usuario.java
        │   ├── Proyecto.java
        │   ├── HistoriaUsuario.java
        │   └── Tarea.java
        └── config/
            └── SwaggerConfig.java             # Configuración Swagger
```

---

## Requisitos previos

- **Java 17** o superior
- **Maven 3.6+**

Verificar instalación:
```bash
java -version
mvn -version
```

---

## Cómo ejecutar el proyecto

**1. Clonar el repositorio**
```bash
git clone https://github.com/brayanroncancio/gestor-de-proyectos.git
cd "gestor-de-proyectos/Arquitectura/gestionproyectos 4"
```

**2. Ejecutar con Maven**
```bash
mvn spring-boot:run
```

**3. Verificar que el servidor está activo**

El servidor inicia en `http://localhost:8080`. Deberías ver en consola:

```
Started GestionproyectosApplication in X.XXX seconds
```

> **Nota:** La base de datos es H2 in-memory. Los datos se pierden al reiniciar el servidor.

---

## Endpoints de la API

Base URL: `http://localhost:8080/api`

### Usuarios

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/usuarios` | Obtener todos los usuarios |
| `GET` | `/usuarios/{id}` | Obtener usuario por ID |
| `POST` | `/usuarios/crear` | Crear nuevo usuario |

### Proyectos

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/proyectos` | Obtener todos los proyectos |
| `GET` | `/proyectos/{id}` | Obtener proyecto por ID |
| `POST` | `/proyectos/crear` | Crear nuevo proyecto |
| `POST` | `/proyectos/{proyectoId}/desarrolladores/{desarrolladorId}/agregar` | Agregar desarrollador al proyecto |

### Historias de Usuario

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/historias-usuario` | Obtener todas las historias |
| `GET` | `/historias-usuario/{id}` | Obtener historia por ID |
| `POST` | `/historias-usuario/crear` | Crear nueva historia |

### Tareas

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/tareas` | Obtener todas las tareas |
| `GET` | `/tareas/{id}` | Obtener tarea por ID |
| `POST` | `/tareas/crear` | Crear nueva tarea |

---

## Guía de uso paso a paso

### Paso 1 — Crear el gerente del proyecto

```http
POST /api/usuarios/crear
Content-Type: application/json

{
  "nombre": "Carlos López",
  "email": "carlos@empresa.com",
  "contrasenia": "segura123",
  "tipo": "gerente"
}
```

**Respuesta `201 Created`:**
```json
{
  "id_usuario": 1,
  "nombre": "Carlos López",
  "email": "carlos@empresa.com",
  "tipo": "gerente"
}
```

---

### Paso 2 — Crear un desarrollador

```http
POST /api/usuarios/crear
Content-Type: application/json

{
  "nombre": "Ana García",
  "email": "ana@empresa.com",
  "contrasenia": "clave456",
  "tipo": "desarrollador"
}
```

**Respuesta `201 Created`:**
```json
{ "id_usuario": 2, "nombre": "Ana García", "tipo": "desarrollador" }
```

---

### Paso 3 — Crear un proyecto

Usa el `id_usuario` del gerente creado en el Paso 1.

```http
POST /api/proyectos/crear
Content-Type: application/json

{
  "nombre": "App de Ventas",
  "descripcion": "Sistema de gestión de ventas en línea",
  "fechaInicio": "2026-03-23",
  "gerente": { "id_usuario": 1 }
}
```

**Respuesta `201 Created`:**
```json
{
  "id_proyecto": 1,
  "nombre": "App de Ventas",
  "gerente": { "id_usuario": 1, "nombre": "Carlos López" },
  "desarrolladores": []
}
```

---

### Paso 4 — Agregar desarrollador al proyecto

```http
POST /api/proyectos/1/desarrolladores/2/agregar
```

No requiere body. Reemplaza `1` con el ID del proyecto y `2` con el ID del desarrollador.

**Respuesta `200 OK`**

---

### Paso 5 — Crear una historia de usuario

```http
POST /api/historias-usuario/crear
Content-Type: application/json

{
  "detalles": "Como usuario quiero registrarme en el sistema",
  "criteriosAceptacion": "Dado que ingreso mis datos válidos, cuando hago clic en registrar, entonces se crea mi cuenta",
  "estado": "pendiente",
  "proyecto": { "id_proyecto": 1 }
}
```

---

### Paso 6 — Crear una tarea

```http
POST /api/tareas/crear
Content-Type: application/json

{
  "descripcion": "Diseñar formulario de registro",
  "estado": "pendiente",
  "historiaUsuario": { "id_Historia": 1 }
}
```

---

### Paso 7 — Consultar tareas con equipo asignado

```http
GET /api/tareas
```

**Respuesta `200 OK`:**
```json
[{
  "id_tarea": 1,
  "descripcion": "Diseñar formulario de registro",
  "estado": "pendiente",
  "historiaUsuario": {
    "id_Historia": 1,
    "detalles": "Como usuario quiero registrarme en el sistema",
    "proyecto": {
      "nombre": "App de Ventas",
      "gerente": {
        "nombre": "Carlos López",
        "tipo": "gerente"
      },
      "desarrolladores": [
        {
          "nombre": "Ana García",
          "tipo": "desarrollador"
        }
      ]
    }
  }
}]
```

---

## Documentación Swagger

Con el servidor corriendo, accede a la documentación interactiva de la API:

```
http://localhost:8080/swagger-ui.html
```

Desde ahí puedes explorar y ejecutar todos los endpoints sin herramientas externas.

---

## Base de datos

La aplicación usa **H2** (base de datos en memoria). El esquema se genera automáticamente al iniciar gracias a `ddl-auto: create-drop`.

**Consola web H2** (para inspeccionar los datos en tiempo real):
```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:gestiondb
Usuario:  sa
Password: a
```

**Esquema de relaciones:**
```
usuario ──< proyecto          (gerente, relación Many-to-One)
usuario >──< proyecto         (desarrolladores, relación Many-to-Many)
proyecto ──< historia_usuario (relación One-to-Many)
historia_usuario ──< tarea    (relación One-to-Many)
```

---

## Tests

Ejecutar las pruebas unitarias:

```bash
mvn test
```

Los tests cubren los principales flujos del controlador usando **Mockito** para aislar las dependencias de la base de datos.

---

## Autor

**Brayan Roncancio**
Universidad Jorge Tadeo Lozano
[brayany.roncacnios@utadeo.edu.co](mailto:brayany.roncacnios@utadeo.edu.co)
