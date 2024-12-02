package com.example.libro.service;

import com.example.libro.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarCategorias();
    Categoria crearCategoria(Categoria categoria);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);

    // Método para obtener una categoría por ID
    Categoria obtenerCategoriaPorId(Long id);
}
