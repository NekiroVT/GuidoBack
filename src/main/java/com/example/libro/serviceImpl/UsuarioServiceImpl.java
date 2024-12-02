package com.example.libro.serviceImpl;

import com.example.libro.entity.Usuario;
import com.example.libro.repository.UsuarioRepository;
import com.example.libro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElse(null); // Devuelve null si no encuentra el usuario
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username); // Verifica si el usuario ya existe
    }
}


