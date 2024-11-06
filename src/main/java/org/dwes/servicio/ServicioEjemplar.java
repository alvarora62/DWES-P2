package org.dwes.servicio;

import org.dwes.modelo.Ejemplar;

import java.util.List;

public interface ServicioEjemplar {

    List<Ejemplar> findAll();
    List<Ejemplar> findByFkPlanta(String codigo);
    boolean save(Ejemplar ejemplar);
    boolean update(Ejemplar ejemplar);
}
