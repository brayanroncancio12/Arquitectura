package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.model.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.model.Tarea;

import java.util.List;

public interface TareaRepository extends JpaRepository <Tarea, Long> {
        List<Tarea> findByHistoriaUsuario(HistoriaUsuario historiaUsuario);
    }
