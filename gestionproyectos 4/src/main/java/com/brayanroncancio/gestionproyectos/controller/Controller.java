package com.brayanroncancio.gestionproyectos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brayanroncancio.gestionproyectos.model.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.model.Proyecto;
import com.brayanroncancio.gestionproyectos.model.Tarea;
import com.brayanroncancio.gestionproyectos.model.Usuario;
import com.brayanroncancio.gestionproyectos.service.HistoriaUsuarioService;
import com.brayanroncancio.gestionproyectos.service.ProyectoService;
import com.brayanroncancio.gestionproyectos.service.TareaService;
import com.brayanroncancio.gestionproyectos.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    UsuarioService usuarioServicio;

    @Autowired
    ProyectoService proyectoServicio;

    @Autowired
    HistoriaUsuarioService historiaServicio;

    @Autowired
    TareaService tareaServicio;


    // ******** ENDPOINTS DE USUARIOS ********

    // GET http://localhost:8080/api/usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/usuarios/1
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // POST http://localhost:8080/api/usuarios/crear
    @PostMapping("/usuarios/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioServicio.crearUsuario(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContrasenia(),
                usuario.getTipo()
        );
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }


    // ******** ENDPOINTS DE PROYECTOS ********

    // GET http://localhost:8080/api/proyectos
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = proyectoServicio.obtenerTodosLosProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/proyectos/1
    @GetMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long id) {
        Proyecto proyecto = proyectoServicio.obtenerProyectoPorId(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    // POST http://localhost:8080/api/proyectos/crear
    @PostMapping("/proyectos/crear")
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto) {
        Proyecto nuevoProyecto = proyectoServicio.crearProyecto(
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getFechaInicio(),
                proyecto.getGerente()
        );
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
    }

    // POST http://localhost:8080/api/proyectos/1/desarrolladores/2/agregar
    @PostMapping("/proyectos/{proyectoId}/desarrolladores/{desarrolladorId}/agregar")
    public ResponseEntity<Void> agregarDesarrolladorAProyecto(
            @PathVariable Long proyectoId,
            @PathVariable Long desarrolladorId) {
        Proyecto proyecto = proyectoServicio.obtenerProyectoPorId(proyectoId);
        Usuario desarrollador = usuarioServicio.obtenerUsuarioPorId(desarrolladorId);
        proyectoServicio.agregarDesarrolladorAProyecto(proyecto, desarrollador);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ******** ENDPOINTS DE HISTORIAS DE USUARIO ********

    // GET http://localhost:8080/api/historias-usuario
    @GetMapping("/historias-usuario")
    public ResponseEntity<List<HistoriaUsuario>> obtenerTodasLasHistorias() {
        List<HistoriaUsuario> historias = historiaServicio.obtenerTodasLasHistorias();
        return new ResponseEntity<>(historias, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/historias-usuario/1
    @GetMapping("/historias-usuario/{id}")
    public ResponseEntity<HistoriaUsuario> obtenerHistoriaUsuarioPorId(@PathVariable Long id) {
        HistoriaUsuario historia = historiaServicio.obtenerHistoriaUsuarioPorId(id);
        return new ResponseEntity<>(historia, HttpStatus.OK);
    }

    // POST http://localhost:8080/api/historias-usuario/crear
    @PostMapping("/historias-usuario/crear")
    public ResponseEntity<HistoriaUsuario> crearHistoriaUsuario(@RequestBody HistoriaUsuario historiaUsuario) {
        HistoriaUsuario nuevaHistoria = historiaServicio.crearHistoriaUsuario(
                historiaUsuario.getDetalles(),
                historiaUsuario.getCriteriosAceptacion(),
                historiaUsuario.getEstado(),
                historiaUsuario.getProyecto()
        );
        return new ResponseEntity<>(nuevaHistoria, HttpStatus.CREATED);
    }


    // ******** ENDPOINTS DE TAREAS ********

    // GET http://localhost:8080/api/tareas
    @GetMapping("/tareas")
    public ResponseEntity<List<Tarea>> obtenerTodasLasTareas() {
        List<Tarea> tareas = tareaServicio.obtenerTodasLasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/tareas/1
    @GetMapping("/tareas/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorId(@PathVariable Long id) {
        Tarea tarea = tareaServicio.obtenerTareaPorId(id);
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }

    // POST http://localhost:8080/api/tareas/crear
    @PostMapping("/tareas/crear")
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaServicio.crearTarea(
                tarea.getDescripcion(),
                tarea.getEstado(),
                tarea.getHistoriaUsuario()
        );
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);
    }
}