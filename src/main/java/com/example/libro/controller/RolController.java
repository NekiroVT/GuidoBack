package com.example.libro.controller;

import com.example.libro.entity.Rol;
import com.example.libro.repository.RolRepository;
import com.example.libro.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Código secreto (para bypass de seguridad)
    private static final String SECRET_KEY = "GuidoPRO";

    // Crear un nuevo rol (validación con código secreto o roles)
    @PostMapping
    public ResponseEntity<?> createRol(
            @RequestHeader(value = "X-Secret-Key", required = false) String secretKey,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @RequestBody Rol rol) {

        // Validar acceso con código secreto
        if (SECRET_KEY.equals(secretKey)) {
            return guardarRol(rol);
        }

        // Validar acceso por roles específicos usando el token
        if (!tieneAccesoPorRol(authorizationHeader)) {
            return ResponseEntity.status(403).body("Acceso denegado. Permisos insuficientes.");
        }

        // Guardar el rol
        return guardarRol(rol);
    }

    // Listar todos los roles (validación con código secreto o roles)
    @GetMapping
    public ResponseEntity<?> getAllRoles(
            @RequestHeader(value = "X-Secret-Key", required = false) String secretKey,
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        // Validar acceso con código secreto
        if (SECRET_KEY.equals(secretKey)) {
            return ResponseEntity.ok(rolRepository.findAll());
        }

        // Validar acceso por roles específicos usando el token
        if (!tieneAccesoPorRol(authorizationHeader)) {
            return ResponseEntity.status(403).body("Acceso denegado. Permisos insuficientes.");
        }

        // Retornar todos los roles
        return ResponseEntity.ok(rolRepository.findAll());
    }

    // Validar roles específicos desde el token
    private boolean tieneAccesoPorRol(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            // Obtener roles del token
            String roles = jwtTokenProvider.getRoles(token);

            // Validar si el usuario tiene alguno de los roles permitidos
            return roles.contains("ADMIN") || roles.contains("CEO") || roles.contains("MODERATOR") 
            		|| roles.contains("PROFESOR");
        } catch (Exception e) {
            return false; // Token inválido o sin los roles requeridos
        }
    }

    // Método para guardar un rol (reutilizable)
    private ResponseEntity<?> guardarRol(Rol rol) {
        // Verificar si el rol ya existe
        if (rolRepository.findByName(rol.getName()) != null) {
            return ResponseEntity.badRequest().body("Error: El rol ya existe.");
        }

        // Guardar el nuevo rol
        Rol savedRol = rolRepository.save(rol);
        return ResponseEntity.ok(savedRol);
    }
}

