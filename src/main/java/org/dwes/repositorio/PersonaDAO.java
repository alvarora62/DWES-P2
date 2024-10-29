package org.dwes.repositorio;

import org.dwes.modelo.Persona;

import java.util.List;

public interface PersonaDAO {

    List<Persona> findAll();
    Persona findById(Long id);
    boolean save(Persona persona);
    boolean delete(Persona persona);
    boolean update(Persona persona);
}
