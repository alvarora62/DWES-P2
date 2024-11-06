package org.dwes.servicio;

import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;

import java.util.List;

public interface ServicioPersona {

    List<Persona> findAll();
    Persona findById(Long id);
    Persona findByEmail(String email);
    boolean save(Persona persona);
    Credenciales checkForAdmin();
}
