package com.brayanroncancio.gestionproyectos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brayanroncancio.gestionproyectos.persistanse.Usuario;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
        Usuario findByNombreOrEmail(String nombre, String email);
    }
