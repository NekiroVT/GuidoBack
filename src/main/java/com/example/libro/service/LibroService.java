package com.example.libro.service;

import com.example.libro.entity.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> listarLibros();
    Libro crearLibro(Libro libro);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);

    // Método para obtener un libro por ID
    Libro obtenerLibroPorId(Long id);
}
