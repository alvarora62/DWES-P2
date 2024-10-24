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
        return "Planta{" +
                "codigo='" + codigo + '\'' +
                ", nombreComun='" + nombreComun + '\'' +
                ", nombreCientifico='" + nombreCientifico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planta planta = (Planta) o;
        return Objects.equals(codigo, planta.codigo) && Objects.equals(nombreComun, planta.nombreComun) && Objects.equals(nombreCientifico, planta.nombreCientifico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombreComun, nombreCientifico);
    }
}
