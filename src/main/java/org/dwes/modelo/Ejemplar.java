package org.dwes.modelo;

import java.util.Objects;

public class Ejemplar {
    private Long id;
    private String nombre;
    private Planta planta;

    public Ejemplar(Long id, Planta planta) {
        this.id = id;
        this.planta = planta;
        this.nombre = generateNombre();
    }

    public Ejemplar() {

    }

    private String generateNombre() {
        return planta.getCodigo() + "_" + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", planta=" + planta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ejemplar ejemplar = (Ejemplar) o;
        return Objects.equals(id, ejemplar.id) && Objects.equals(nombre, ejemplar.nombre) && Objects.equals(planta, ejemplar.planta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, planta);
    }
}