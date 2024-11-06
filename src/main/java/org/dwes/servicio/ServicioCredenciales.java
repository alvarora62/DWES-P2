package org.dwes.servicio;

import org.dwes.modelo.Credenciales;

public interface ServicioCredenciales {

    Credenciales findByUsername(String username);
    boolean save(Credenciales credenciales);
    int login(String username, String password);
}
