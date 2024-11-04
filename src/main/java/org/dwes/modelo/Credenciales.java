package org.dwes.modelo;

public class Credenciales {

    private Long id;
    private String usuario;
    private String password;
    private Persona fk_persona;

    public Credenciales(Long id, String usuario, String password, Persona fk_persona) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.fk_persona = fk_persona;
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
    public String toString() {
        return "Credenciales{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", fk_persona=" + fk_persona +
                '}';
    }
}
