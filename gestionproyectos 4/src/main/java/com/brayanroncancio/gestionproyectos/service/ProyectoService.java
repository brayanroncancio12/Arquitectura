package com.brayanroncancio.gestionproyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.model.Proyecto;
import com.brayanroncancio.gestionproyectos.model.Usuario;
import com.brayanroncancio.gestionproyectos.repository.ProyectoRepository;
import com.brayanroncancio.gestionproyectos.repository.UsuarioRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepositorio;

    @Autowired
    UsuarioRepository usuarioRepositorio;

    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoRepositorio.findAll();
    }

    public Proyecto obtenerProyectoPorId(Long proyectoId) {
        return proyectoRepositorio.findById(proyectoId).orElseThrow(() ->
                new NoSuchElementException("Proyecto no encontrado: " + proyectoId));
    }

    public Proyecto crearProyecto(String nombre, String descripcion, String fechaInicio, Usuario gerente) {
        // Busca el gerente completo en la BD usando su ID
        // Así la respuesta muestra todos sus datos, no nulls
        Usuario gerenteCompleto = usuarioRepositorio.findById(gerente.getId_usuario())
                .orElseThrow(() -> new NoSuchElementException("Gerente no encontrado: " + gerente.getId_usuario()));

        Proyecto nuevoProyecto = new Proyecto();
        nuevoProyecto.setNombre(nombre);
        nuevoProyecto.setDescripcion(descripcion);
        nuevoProyecto.setFechaInicio(fechaInicio);
        nuevoProyecto.setGerente(gerenteCompleto);

        return proyectoRepositorio.save(nuevoProyecto);
    }

    public void agregarDesarrolladorAProyecto(Proyecto proyecto, Usuario desarrollador) {
        if (!proyecto.getDesarrolladores().contains(desarrollador)) {
            proyecto.getDesarrolladores().add(desarrollador);
            proyectoRepositorio.save(proyecto);
        }
    }
}