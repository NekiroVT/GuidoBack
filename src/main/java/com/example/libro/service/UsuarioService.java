package com.example.libro.service;

import com.example.libro.entity.Usuario;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    Usuario findByUsername(String username);
    boolean existsByUsername(String username); // Nuevo método agregado
}
