package org.dwes.servicio;

import org.dwes.modelo.Planta;

import java.util.List;

public interface ServicioPlanta {

    List<Planta> listarPlantas();
    void save(Planta planta);
    void delete(String codigo);
    void update(Planta planta);
}
