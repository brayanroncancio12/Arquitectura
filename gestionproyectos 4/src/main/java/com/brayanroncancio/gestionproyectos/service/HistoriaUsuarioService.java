package com.brayanroncancio.gestionproyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Proyecto;
import com.brayanroncancio.gestionproyectos.repository.HistoriaUsuarioRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HistoriaUsuarioService {
    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepositorio;

    public List<HistoriaUsuario> obtenerTodasLasHistorias() {
        return historiaUsuarioRepositorio.findAll();
    }

    public HistoriaUsuario obtenerHistoriaUsuarioPorId(Long historiaId) {
        return historiaUsuarioRepositorio.findById(historiaId).orElseThrow(() ->
                new NoSuchElementException("Proyecto no encontrado " + historiaId));
    }

    public HistoriaUsuario crearHistoriaUsuario(String detalles, String criteriosAceptacion, String estado, Proyecto proyecto) {
        HistoriaUsuario nuevaHistoria = new HistoriaUsuario();
        nuevaHistoria.setDetalles(detalles);
        nuevaHistoria.setCriteriosAceptacion(criteriosAceptacion);
        nuevaHistoria.setEstado(estado);
        nuevaHistoria.setProyecto(proyecto);

        return historiaUsuarioRepositorio.save(nuevaHistoria);
    }
}
