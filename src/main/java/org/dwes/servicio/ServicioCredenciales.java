package org.dwes.servicio;

import org.dwes.modelo.Credenciales;

public interface ServicioCredenciales {

    boolean save(Credenciales credenciales);
    int login(String username, String password);
}
