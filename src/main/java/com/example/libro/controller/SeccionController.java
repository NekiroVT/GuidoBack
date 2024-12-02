package com.example.libro.controller;

import com.example.libro.entity.Seccion;
import com.example.libro.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secciones")
@CrossOrigin(origins = "http://localhost:4200")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<List<Seccion>> listarSecciones() {
        return ResponseEntity.ok(seccionService.listarSecciones());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<Seccion> obtenerSeccionPorId(@PathVariable Long id) {
        Seccion seccion = seccionService.obtenerSeccionPorId(id);
        return ResponseEntity.ok(seccion);
    }

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Seccion> crearSeccion(@RequestBody Seccion seccion) {
        return ResponseEntity.ok(seccionService.crearSeccion(seccion));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Seccion> actualizarSeccion(@PathVariable Long id, @RequestBody Seccion seccion) {
        return ResponseEntity.ok(seccionService.actualizarSeccion(id, seccion));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }
}
