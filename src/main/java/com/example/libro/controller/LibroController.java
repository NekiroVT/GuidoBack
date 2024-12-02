package com.example.libro.controller;

import com.example.libro.entity.Libro;
import com.example.libro.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerLibroPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, libro));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
