package org.dwes.modelo;

import java.util.Objects;

public class Planta {

    private String codigo;
    private String nombreComun;
    private String nombreCientifico;

    public Planta(String codigo, String nombreComun, String nombreCientifico) {
        this.codigo = codigo;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
    }

    public Planta() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\tNombre Comun: " + nombreComun + "\tNombre Cientifico: " + nombreCientifico;
    }
}
