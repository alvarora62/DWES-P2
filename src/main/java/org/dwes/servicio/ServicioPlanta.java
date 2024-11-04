package org.dwes.servicio;

import org.dwes.modelo.Planta;

import java.util.List;

public interface ServicioPlanta {

    List<Planta> listarPlantas();
    Planta findByCodigo(String codigo);
    boolean save(Planta planta);
    void delete(String codigo);
    boolean update(Planta planta);
}
