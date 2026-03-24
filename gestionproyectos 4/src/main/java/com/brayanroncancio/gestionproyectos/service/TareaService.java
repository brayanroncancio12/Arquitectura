package com.brayanroncancio.gestionproyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.model.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.model.Tarea;
import com.brayanroncancio.gestionproyectos.repository.HistoriaUsuarioRepository;
import com.brayanroncancio.gestionproyectos.repository.TareaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TareaService {

    @Autowired
    TareaRepository tareaRepositorio;

    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepositorio;

    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepositorio.findAll();
    }

    public Tarea obtenerTareaPorId(Long tareaId) {
        return tareaRepositorio.findById(tareaId).orElseThrow(() ->
                new NoSuchElementException("Tarea no encontrada: " + tareaId));
    }

    public Tarea crearTarea(String descripcion, String estado, HistoriaUsuario historiaUsuario) {
        // Busca la historia completa en la BD usando su ID
        HistoriaUsuario historiaCompleta = historiaUsuarioRepositorio.findById(historiaUsuario.getId_Historia())
                .orElseThrow(() -> new NoSuchElementException("Historia no encontrada: " + historiaUsuario.getId_Historia()));

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setEstado(estado);
        nuevaTarea.setHistoriaUsuario(historiaCompleta);

        return tareaRepositorio.save(nuevaTarea);
    }
}