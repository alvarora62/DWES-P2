package org.dwes.servicio;

import org.dwes.modelo.Credenciales;

public interface ServicioCredenciales {

    Credenciales findByUsuario(String user);
    boolean save(Credenciales credenciales);
}
