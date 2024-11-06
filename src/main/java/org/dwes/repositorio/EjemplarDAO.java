package org.dwes.repositorio;

import org.dwes.modelo.Ejemplar;

import java.util.List;

public interface EjemplarDAO {

    List<Ejemplar> findAll();
    List<Ejemplar> findByFkPlanta(String codigo);
    boolean save(Ejemplar ejemplar);
    boolean update(Ejemplar ejemplar);
}
