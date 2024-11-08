package org.dwes.modelo;

public class Ejemplar {
    private Long id;
    private String nombre;
    private Planta planta;

    public Ejemplar(Long id, String nombre, Planta planta) {
        this.id = id;
        this.nombre = nombre;
        this.planta = planta;
    }

    public Ejemplar() {

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
        return "ID: " + id + " | Nombre: " + nombre + " | Planta: " + planta.getCodigo();
    }


}