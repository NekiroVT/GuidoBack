package com.example.libro.service;

import com.example.libro.entity.Seccion;
import java.util.List;

public interface SeccionService {
    List<Seccion> listarSecciones();
    Seccion crearSeccion(Seccion seccion);
    Seccion actualizarSeccion(Long id, Seccion seccion);
    void eliminarSeccion(Long id);

    // Método para obtener una sección por ID
    Seccion obtenerSeccionPorId(Long id);
}
