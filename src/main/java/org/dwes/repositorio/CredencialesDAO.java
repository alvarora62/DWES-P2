package org.dwes.repositorio;

import org.dwes.modelo.Credenciales;

import java.util.List;

public interface CredencialesDAO {

    List<Credenciales> findAll();
    Credenciales findByUsuario(String user);
    boolean save(Credenciales credenciales);
}
