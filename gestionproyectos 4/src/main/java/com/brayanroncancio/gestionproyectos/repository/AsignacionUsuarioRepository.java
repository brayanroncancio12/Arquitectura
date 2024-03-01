package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.persistanse.AsignacionUsuario;

public interface AsignacionUsuarioRepository extends JpaRepository <AsignacionUsuario, Long> {
    
}
