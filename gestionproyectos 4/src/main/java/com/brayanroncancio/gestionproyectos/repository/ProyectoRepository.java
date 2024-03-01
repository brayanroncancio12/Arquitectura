package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.persistanse.Proyecto;
import com.brayanroncancio.gestionproyectos.persistanse.Usuario;

import java.util.List;

public interface ProyectoRepository extends JpaRepository <Proyecto, Long> {
        List<Proyecto> findByGerenteOrDesarrolladores(Usuario gerente, Usuario desarrollador);
    }
