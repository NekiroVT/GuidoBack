package com.example.libro.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = true, length = 50)
    private String edicion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Integer paginas;

    @ManyToOne
    @JoinColumn(name = "editorial_id", nullable = false)
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "seccion_id", nullable = false)
    private Seccion seccion;

    // Constructor vacío
    public Libro() {}

    // Constructor con parámetros (opcional, útil para pruebas)
    public Libro(String titulo, String edicion, String estado, Integer paginas, Editorial editorial, Seccion seccion) {
        this.titulo = titulo;
        this.edicion = edicion;
        this.estado = estado;
        this.paginas = paginas;
        this.editorial = editorial;
        this.seccion = seccion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
}

