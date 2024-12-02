package com.example.libro.serviceImpl;

import com.example.libro.entity.Editorial;
import com.example.libro.entity.Libro;
import com.example.libro.entity.Seccion;
import com.example.libro.repository.EditorialRepository;
import com.example.libro.repository.LibroRepository;
import com.example.libro.repository.SeccionRepository;
import com.example.libro.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro crearLibro(Libro libro) {
        // Verificar si la editorial existe
        Editorial editorial = editorialRepository.findById(libro.getEditorial().getId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada con ID: " + libro.getEditorial().getId()));

        // Verificar si la sección existe
        Seccion seccion = seccionRepository.findById(libro.getSeccion().getId())
                .orElseThrow(() -> new RuntimeException("Sección no encontrada con ID: " + libro.getSeccion().getId()));

        // Asignar las relaciones al libro
        libro.setEditorial(editorial);
        libro.setSeccion(seccion);

        // Guardar el libro
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setEdicion(libro.getEdicion());
        libroExistente.setEstado(libro.getEstado());
        libroExistente.setPaginas(libro.getPaginas());

        // Actualizar la editorial si es necesario
        Editorial editorial = editorialRepository.findById(libro.getEditorial().getId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada con ID: " + libro.getEditorial().getId()));
        libroExistente.setEditorial(editorial);

        // Actualizar la sección si es necesario
        Seccion seccion = seccionRepository.findById(libro.getSeccion().getId())
                .orElseThrow(() -> new RuntimeException("Sección no encontrada con ID: " + libro.getSeccion().getId()));
        libroExistente.setSeccion(seccion);

        return libroRepository.save(libroExistente);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }
}
