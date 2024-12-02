package com.example.libro.dto;

import com.example.libro.entity.Rol;
import java.util.Set;

public class UsuarioRegistroDTO {
    private Long id;
    private String username;
    private String password; // Contraseña encriptada
    private Set<Rol> roles;

    // Constructor, Getters y Setters
    public UsuarioRegistroDTO(Long id, String username, String password, Set<Rol> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
