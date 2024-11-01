package org.dwes.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {

    private Long id;
    private LocalDateTime fechaHora;
    private String mensaje;
    private Persona persona;
    private Ejemplar ejemplar;

    public Mensaje(Long id, Ejemplar ejemplar, Persona persona, String mensaje, LocalDateTime fechaHora) {
        this.id = id;
        this.ejemplar = ejemplar;
        this.persona = persona;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }

    public Mensaje() {
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", mensaje='" + mensaje + '\'' +
                ", persona=" + persona +
                ", ejemplar=" + ejemplar +
                '}';
    }


}
