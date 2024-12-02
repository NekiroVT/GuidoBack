package com.example.libro.serviceImpl;

import com.example.libro.entity.Categoria;
import com.example.libro.repository.CategoriaRepository;
import com.example.libro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setEstado(categoria.getEstado());
        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
    
    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

}
