package org.dwes.servicio;

import org.dwes.modelo.Ejemplar;

import java.util.List;

public interface ServicioEjemplar {

    List<Ejemplar> findAll();
    Ejemplar findById(Long id);
    List<Ejemplar> findByFkPlanta(String codigo);
    boolean save(Ejemplar ejemplar);
    boolean update(Ejemplar ejemplar);
}
