package org.dwes.modelo;

import java.util.Objects;

public class Persona {

    private Long id;
    private String nombre;
    private String email;

    public Persona(String email, Long id, String nombre) {
        this.email = email;
        this.id = id;
        this.nombre = nombre;
    }

    public Persona() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
