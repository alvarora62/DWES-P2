package org.dwes.repositorio;

import org.dwes.modelo.Planta;

import java.util.List;

public interface PlantaDAO {

    List<Planta> findAll();
    Planta findById(String codigo);
    boolean save(Planta planta);
    boolean delete(Planta planta);
    boolean update(Planta planta);
}
