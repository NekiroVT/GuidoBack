package com.example.libro.dto;

import com.example.libro.entity.Rol;
import java.util.Set;

public class UsuarioLoginDTO {
    private Long id;
    private String username;
    private Set<Rol> roles; // Roles del usuario
    private String token; // Token JWT

    // Constructor, Getters y Setters
    public UsuarioLoginDTO(Long id, String username, Set<Rol> roles, String token) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.token = token;
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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
