package org.dwes.servicio;

import org.dwes.modelo.Persona;

import java.util.List;

public interface ServicioPersona {

    List<Persona> findAll();
    Persona findById(Long id);
    boolean save(Persona persona);
}
