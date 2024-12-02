package com.example.libro.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Público: Registro y login
                .requestMatchers("/api/roles/**").permitAll() // Público: CRUD de roles
                .requestMatchers("/api/libros/**").permitAll() // Público: CRUD de libros
                .requestMatchers("/api/usuarios/**").permitAll() // Público: Manejo de usuarios
                .requestMatchers("/api/categorias/**").permitAll() // Público: CRUD de categorías
                .requestMatchers("/api/editoriales/**").permitAll() // Público: CRUD de editoriales
                .requestMatchers("/api/secciones/**").permitAll() // Público: CRUD de secciones
                .requestMatchers("/api/ejemplo/**").authenticated() //Protegido: CRUD de ejemploooo
                .anyRequest().authenticated() // Protegido: El resto requiere autenticación
            )
            .userDetailsService(customUserDetailsService)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

