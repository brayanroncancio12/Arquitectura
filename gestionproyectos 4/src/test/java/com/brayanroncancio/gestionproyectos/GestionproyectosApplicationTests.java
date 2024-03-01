package com.brayanroncancio.gestionproyectos;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.brayanroncancio.gestionproyectos.controller.Controller;
import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Proyecto;
import com.brayanroncancio.gestionproyectos.persistanse.Tarea;
import com.brayanroncancio.gestionproyectos.persistanse.Usuario;
import com.brayanroncancio.gestionproyectos.repository.HistoriaUsuarioRepository;
import com.brayanroncancio.gestionproyectos.repository.ProyectoRepository;
import com.brayanroncancio.gestionproyectos.repository.TareaRepository1;
import com.brayanroncancio.gestionproyectos.service.ProyectoService;
import com.brayanroncancio.gestionproyectos.service.TareaService;
import com.brayanroncancio.gestionproyectos.service.UsuarioService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ControladorPrincipalTest {

    @Mock
    private UsuarioService usuarioServicio;

    @Mock
    private ProyectoService proyectoServicio;
    @Mock
    private ProyectoRepository proyectoRepositorio;

    @Mock
    private HistoriaUsuario historiaServicio;
    @Mock
    private HistoriaUsuarioRepository historiaUsuarioRepositorio;

    @Mock
    private TareaService tareaServicio;
    @Mock
    private TareaRepository1 tareaRepositorio;

    @InjectMocks
    private Controller controladorPrincipal;

    // ******** UNIT TEST PARA ENDPOINTS RELACIONADO CON USUARIOS *************

    @Test
    void obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioServicio.obtenerTodosLosUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> responseEntity = controladorPrincipal.obtenerTodosLosUsuarios();
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuarios, responseEntity.getBody());
    }

    @Test
    void obtenerUsuarioPorId() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        when(usuarioServicio.obtenerUsuarioPorId(userId)).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = controladorPrincipal.obtenerUsuarioPorId(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    void crearUsuario() {
        Usuario usuario = new Usuario(3L, "Loki", "loki@cosmo.com", "password", "desarrollador");
        when(usuarioServicio.crearUsuario(any(), any(), any(), any())).thenReturn(usuario);

        ResponseEntity<Usuario> responseEntity = controladorPrincipal.crearUsuario(usuario);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    // ******** UNIT TESTS PARA ENDPOINTS RELACIONADOS CON PROYECTOS *************

    // ******** UNIT TESTS PARA ENDPOINTS RELACIONADOS CON HISTORIAS DE USUARIO *************

    @Test
    void obtenerTodasLasHistorias() {
        List<HistoriaUsuario> historias = Arrays.asList(new HistoriaUsuario(), new HistoriaUsuario());
        when(historiaUsuarioRepositorio.findAll()).thenReturn(historias);

        List<HistoriaUsuario> result = historiaServicio.obtenerTodasLasHistorias();

        assertEquals(historias.size(), result.size());
        verify(historiaUsuarioRepositorio, times(1)).findAll();
    }

    @Test
    void obtenerHistoriaUsuarioPorId() {
        Long historiaId = 1L;
        HistoriaUsuario historia = new HistoriaUsuario();
        when(historiaUsuarioRepositorio.findById(historiaId)).thenReturn(Optional.of(historia));

        HistoriaUsuario result = historiaServicio.obtenerHistoriaUsuarioPorId(historiaId);

        assertEquals(historia, result);
        verify(historiaUsuarioRepositorio, times(1)).findById(historiaId);
    }

    @Test
    void crearHistoriaUsuario() {
        HistoriaUsuario nuevaHistoria = new HistoriaUsuario(1L, "Detalles de la historia", "Criterios de aceptación", "En progreso", new Proyecto());
        when(historiaUsuarioRepositorio.save(any())).thenReturn(nuevaHistoria);

        HistoriaUsuario result = historiaServicio.crearHistoriaUsuario(nuevaHistoria.getDetalles(), nuevaHistoria.getCriteriosAceptacion(), nuevaHistoria.getEstado(), nuevaHistoria.getProyecto());

        assertEquals(nuevaHistoria, result);
        verify(historiaUsuarioRepositorio, times(1)).save(any());
    }


    // ******** UNIT TESTS PARA ENDPOINTS RELACIONADOS CON TAREAS *************

    @Test
    void obtenerTodasLasTareas() {
        List<Tarea> tareas = Arrays.asList(new Tarea(), new Tarea());
        when(tareaRepositorio.findAll()).thenReturn(tareas);

        List<Tarea> result = tareaServicio.obtenerTodasLasTareas();

        assertEquals(tareas.size(), result.size());
        verify(tareaRepositorio, times(1)).findAll();
    }

    @Test
    void obtenerTareaPorId() {
        Long tareaId = 1L;
        Tarea tarea = new Tarea();
        when(tareaRepositorio.findById(tareaId)).thenReturn(Optional.of(tarea));

        Tarea result = tareaServicio.obtenerTareaPorId(tareaId);

        assertEquals(tarea, result);
        verify(tareaRepositorio, times(1)).findById(tareaId);
    }

    @Test
    void crearTarea() {
        Tarea nuevaTarea = new Tarea(1L,"Descripción de la tarea", "Pendiente", new HistoriaUsuario());
        when(tareaRepositorio.save(any())).thenReturn(nuevaTarea);

        Tarea result = tareaServicio.crearTarea(nuevaTarea.getDescripcion(), nuevaTarea.getEstado(), nuevaTarea.getHistoriaUsuario());

        assertEquals(nuevaTarea, result);
        verify(tareaRepositorio, times(1)).save(any());
    }
}
