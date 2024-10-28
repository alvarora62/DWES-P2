package org.dwes.servicio;

import org.dwes.modelo.Planta;
import org.dwes.repositorio.PlantaDAOImpl;

import java.util.List;

public class ServicioPlantaImpl implements ServicioPlanta{

    private PlantaDAOImpl plantaDAO;

    @Override
    public List<Planta> listarPlantas() {
        return plantaDAO.findAll();
    }
}
