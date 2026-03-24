package com.brayanroncancio.gestionproyectos.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brayanroncancio.gestionproyectos.model.Usuario;
import com.brayanroncancio.gestionproyectos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepositorio;

    // Trae todos los usuarios de la BD
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // Busca un usuario por su ID, lanza error si no existe
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado: " + id));
    }

    // Crea y guarda un nuevo usuario en la BD
    public Usuario crearUsuario(String nombre, String email, String contrasenia, String tipo) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setContrasenia(contrasenia);
        nuevoUsuario.setTipo(tipo);
        return usuarioRepositorio.save(nuevoUsuario);
    }
}