package com.example.libro.serviceImpl;

import com.example.libro.entity.Editorial;
import com.example.libro.repository.EditorialRepository;
import com.example.libro.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public List<Editorial> listarEditoriales() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial crearEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Editorial actualizarEditorial(Long id, Editorial editorial) {
        Editorial editorialExistente = editorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada con ID: " + id));
        editorialExistente.setNombre(editorial.getNombre());
        editorialExistente.setEstado(editorial.getEstado());
        return editorialRepository.save(editorialExistente);
    }

    @Override
    public void eliminarEditorial(Long id) {
        editorialRepository.deleteById(id);
    }
    
    @Override
    public Editorial obtenerEditorialPorId(Long id) {
        return editorialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada con ID: " + id));
    }
}

