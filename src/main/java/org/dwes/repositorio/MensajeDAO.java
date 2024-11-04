package org.dwes.repositorio;

import org.dwes.modelo.Persona;

import java.util.List;

public interface MensajeDAO {

    List<Persona> findAll();
    Persona findById(Long id);
    Persona findByEmail(String mail);
    boolean save(Persona persona);
}
