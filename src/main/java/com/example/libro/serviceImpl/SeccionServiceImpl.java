package com.example.libro.serviceImpl;

import com.example.libro.entity.Categoria;
import com.example.libro.entity.Seccion;
import com.example.libro.repository.CategoriaRepository;
import com.example.libro.repository.SeccionRepository;
import com.example.libro.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public List<Seccion> listarSecciones() {
        return seccionRepository.findAll();
    }
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Seccion crearSeccion(Seccion seccion) {
        // Verificar si la categoría existe en la base de datos
        Categoria categoria = categoriaRepository.findById(seccion.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + seccion.getCategoria().getId()));

        // Asignar la categoría completa a la sección
        seccion.setCategoria(categoria);

        // Guardar la sección
        return seccionRepository.save(seccion);
    }


    @Override
    public Seccion actualizarSeccion(Long id, Seccion seccion) {
        Seccion seccionExistente = seccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección no encontrada con ID: " + id));
        seccionExistente.setNombre(seccion.getNombre());
        seccionExistente.setEstado(seccion.getEstado());
        seccionExistente.setCategoria(seccion.getCategoria());
        return seccionRepository.save(seccionExistente);
    }

    @Override
    public void eliminarSeccion(Long id) {
        seccionRepository.deleteById(id);
    }
    
    @Override
    public Seccion obtenerSeccionPorId(Long id) {
        return seccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección no encontrada con ID: " + id));
    }
}

