package com.example.libro.controller;

import com.example.libro.entity.Rol;
import com.example.libro.entity.Usuario;
import com.example.libro.repository.RolRepository;
import com.example.libro.service.UsuarioService;
import com.example.libro.config.JwtTokenProvider;
import com.example.libro.dto.UsuarioLoginDTO;
import com.example.libro.dto.UsuarioRegistroDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioService.existsByUsername(usuario.getUsername())) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso.");
        }

        // Encriptar la contraseña del usuario
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar roles (como ya configuraste)
        Set<Rol> roles = usuario.getRoles().stream()
                .map(rol -> rolRepository.findById(rol.getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + rol.getId())))
                .collect(Collectors.toSet());
        usuario.setRoles(roles);

        // Guardar el usuario
        Usuario usuarioGuardado = usuarioService.saveUsuario(usuario);

        // Construir DTO de respuesta
        UsuarioRegistroDTO response = new UsuarioRegistroDTO(
                usuarioGuardado.getId(),
                usuarioGuardado.getUsername(),
                usuarioGuardado.getPassword(),
                usuarioGuardado.getRoles()
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDTO> login(@RequestBody Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
        );

        // Generar el token JWT
        String token = jwtTokenProvider.generateToken(authentication);

        // Obtener los datos del usuario autenticado
        Usuario usuarioAutenticado = usuarioService.findByUsername(usuario.getUsername());

        // Construir DTO de respuesta
        UsuarioLoginDTO response = new UsuarioLoginDTO(
                usuarioAutenticado.getId(),
                usuarioAutenticado.getUsername(),
                usuarioAutenticado.getRoles(), // Roles
                token // Token JWT
        );

        return ResponseEntity.ok(response);
    }

}


