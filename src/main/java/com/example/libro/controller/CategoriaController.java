package com.example.libro.controller;

import com.example.libro.entity.Categoria;
import com.example.libro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.crearCategoria(categoria));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

