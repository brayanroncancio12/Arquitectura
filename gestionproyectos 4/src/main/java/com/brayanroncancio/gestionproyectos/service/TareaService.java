package com.brayanroncancio.gestionproyectos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Tarea;
import com.brayanroncancio.gestionproyectos.repository.TareaRepository1;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TareaService {
    @Autowired
    TareaRepository1 tareaRepositorio;


    public List<Tarea> obtenerTodasLasTareas(){
        return tareaRepositorio.findAll();
    }

    public Tarea obtenerTareaPorId(Long tareaId) {
        return tareaRepositorio.findById(tareaId).orElseThrow(()->
                new NoSuchElementException("Proyecto no encontrado " + tareaId));
    }

    public Tarea crearTarea(String descripcion, String estado, HistoriaUsuario historiaUsuario) {
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setEstado(estado);
        nuevaTarea.setHistoriaUsuario(historiaUsuario);

        return tareaRepositorio.save(nuevaTarea);
    }


}
