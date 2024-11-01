package org.dwes.repositorio;

import org.dwes.modelo.Planta;

import java.util.List;

public interface PlantaDAO {

    List<Planta> findAll();
    Planta findById(String codigo);
    boolean save(Planta planta);
    boolean delete(String codigo);
    boolean update(Planta planta);
}
