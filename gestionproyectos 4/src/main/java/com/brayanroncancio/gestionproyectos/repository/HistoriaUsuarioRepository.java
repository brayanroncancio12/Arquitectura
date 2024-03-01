package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.persistanse.HistoriaUsuario;
import com.brayanroncancio.gestionproyectos.persistanse.Proyecto;

import java.util.List;

public interface HistoriaUsuarioRepository extends JpaRepository <HistoriaUsuario, Long> {
        List<HistoriaUsuario> findByProyecto(Proyecto proyecto);
    }
