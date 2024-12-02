package com.example.libro.repository;

import com.example.libro.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Long> {
}
