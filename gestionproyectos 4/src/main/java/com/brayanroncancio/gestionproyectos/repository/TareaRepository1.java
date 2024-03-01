package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Tarea;

import java.util.List;

public interface TareaRepository1 extends JpaRepository <Tarea, Long> {
        List<Tarea> findByHistoriaUsuario(HistoriaUsuario historiaUsuario);
    }
