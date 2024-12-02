package com.example.libro.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "X1p2ZlY4c3p5RkVKMmdnTklMN1c4R2tDNDhtU3AzajFyODVZaEY2QnlUZXhXOA==";
    private final long EXPIRATION_TIME = 3600000; // 1 hora en milisegundos

    // Obtener la clave secreta como un objeto Key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generar token a partir de la autenticación
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        // Obtener roles del usuario autenticado
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // Roles separados por comas

        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username) // Username del usuario
                .claim("roles", roles) // Agregar roles como claim
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Generar token directamente con el nombre de usuario
    public String generateTokenWithUsername(String username, String roles) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username) // Username del usuario
                .claim("roles", roles) // Agregar roles como claim
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener el nombre de usuario (subject) del token
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Obtener los roles del token
    public String getRoles(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles", String.class); // Suponiendo que los roles están en el claim "roles"
    }

    // Validar si un token es válido
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            // Token inválido
            return false;
        }
    }
}
