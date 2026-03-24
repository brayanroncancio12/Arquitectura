package com.brayanroncancio.gestionproyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.model.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.model.Proyecto;
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
                new NoSuchElementException("Historia de usuario no encontrada: " + historiaId));
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
