package com.example.libro.controller;

import com.example.libro.entity.Editorial;
import com.example.libro.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
@CrossOrigin(origins = "http://localhost:4200")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<List<Editorial>> listarEditoriales() {
        return ResponseEntity.ok(editorialService.listarEditoriales());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'GERENTE')")
    public ResponseEntity<Editorial> obtenerEditorialPorId(@PathVariable Long id) {
        Editorial editorial = editorialService.obtenerEditorialPorId(id);
        return ResponseEntity.ok(editorial);
    }

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Editorial> crearEditorial(@RequestBody Editorial editorial) {
        return ResponseEntity.ok(editorialService.crearEditorial(editorial));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Editorial> actualizarEditorial(@PathVariable Long id, @RequestBody Editorial editorial) {
        return ResponseEntity.ok(editorialService.actualizarEditorial(id, editorial));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> eliminarEditorial(@PathVariable Long id) {
        editorialService.eliminarEditorial(id);
        return ResponseEntity.noContent().build();
    }
}
