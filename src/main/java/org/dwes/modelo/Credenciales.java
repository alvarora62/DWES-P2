package org.dwes.modelo;

import java.util.Objects;

public class Credenciales {

    private Long id;
    private String usuario;
    private String password;
    private Persona fk_persona;

    public Credenciales(Long id, String password, Persona fk_persona, String usuario) {
        this.id = id;
        this.password = password;
        this.fk_persona = fk_persona;
        this.usuario = usuario;
    }

    public Credenciales() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getFk_persona() {
        return fk_persona;
    }

    public void setFk_persona(Persona fk_persona) {
        this.fk_persona = fk_persona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credenciales that = (Credenciales) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(password, that.password) && Objects.equals(fk_persona, that.fk_persona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, password, fk_persona);
    }

    @Override
    public String toString() {
        return "Credenciales{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", fk_persona=" + fk_persona +
                '}';
    }
}
