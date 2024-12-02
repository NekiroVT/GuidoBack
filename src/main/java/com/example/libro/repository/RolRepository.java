package com.example.libro.repository;

import com.example.libro.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
	Rol findByName(String name); // Método para buscar un rol por su nombre
}
