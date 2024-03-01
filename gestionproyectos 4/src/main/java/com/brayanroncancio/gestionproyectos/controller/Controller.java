package com.brayanroncancio.gestionproyectos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Proyecto;
import com.brayanroncancio.gestionproyectos.persistanse.Tarea;
import com.brayanroncancio.gestionproyectos.persistanse.Usuario;
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


    // ******** ENDPOINTS RELACIONADOS A USUARIOS *************

    @GetMapping("/usuarios")
    public ResponseEntity <List<Usuario>> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios = usuarioServicio.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{Id_usuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long Id_usuario){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(Id_usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    @PostMapping("/usuarios/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioServicio.crearUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getContrasenia(), usuario.getTipo());
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
    // *********** ENDPOINTS RELACIONADOS A PROYECTOS *************

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> obtenerTodosLosProyectos() {
        List<Proyecto> proyectos = proyectoServicio.obtenerTodosLosProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @GetMapping("/proyectos/{Id_proyecto}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long Id_proyecto){
        Proyecto proyecto = proyectoServicio.obtenerProyectoPorId(Id_proyecto);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

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


    @PostMapping("/proyectos/{proyectoId}/desarrolladores/{desarrolladorId}/crear")
    public ResponseEntity<Void> agregarDesarrolladorAProyecto(@PathVariable Long proyectoId, @PathVariable Long desarrolladorId) {
        Proyecto proyecto = proyectoServicio.obtenerProyectoPorId(proyectoId);
        Usuario desarrollador = usuarioServicio.obtenerUsuarioPorId(desarrolladorId);

        proyectoServicio.agregarDesarrolladorAProyecto(proyecto, desarrollador);

        return new ResponseEntity<>(HttpStatus.OK);
    }

     // ******** ENPOINTS RELACIONADOS A HISTORIAS DE USUARIO *********
    
    @GetMapping("/historias-usuario")
    public ResponseEntity<List<HistoriaUsuario>> obtenerTodasLasHistorias(){
        List<HistoriaUsuario> historiaUsuarios = historiaServicio.obtenerTodasLasHistorias();
        return new ResponseEntity<>(historiaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/historias-usuario/{Id_historia}")
    public ResponseEntity<HistoriaUsuario> obtenerHistoriaUsuarioPorId(@PathVariable Long historiaId) {
        HistoriaUsuario historiaUsuario = historiaServicio.obtenerHistoriaUsuarioPorId(historiaId);
        return new ResponseEntity<>(historiaUsuario, HttpStatus.OK);
    }


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

    //*********** ENDPOINTS RELACIONADOS A TAREAS *****************

    @GetMapping("/tareas")
    public ResponseEntity <List<Tarea>> obtenerTodasLasTareas(){
        List<Tarea> tareas = tareaServicio.obtenerTodasLasTareas();
        return new ResponseEntity<>(tareas, HttpStatus.OK);

    }

    @GetMapping ("/tareas/{Id_tarea}")
    public ResponseEntity <Tarea> obtenerTareaPorId(@PathVariable Long tareaId){
        Tarea tarea = tareaServicio.obtenerTareaPorId(tareaId);
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }

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
