package com.example.libro.service;

import com.example.libro.entity.Editorial;
import java.util.List;

public interface EditorialService {
    List<Editorial> listarEditoriales();
    Editorial crearEditorial(Editorial editorial);
    Editorial actualizarEditorial(Long id, Editorial editorial);
    void eliminarEditorial(Long id);

    // Método para obtener una editorial por ID
    Editorial obtenerEditorialPorId(Long id);
}
