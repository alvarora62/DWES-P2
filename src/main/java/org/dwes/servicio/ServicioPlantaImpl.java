package org.dwes.servicio;

import org.dwes.modelo.Planta;
import org.dwes.repositorio.PlantaDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPlantaImpl implements ServicioPlanta {

    private static ServicioPlantaImpl servicioPlanta;
    private PlantaDAOImpl plantaDAO;
    private final String codigoPattern = "^[A-Z]$";

    private ServicioPlantaImpl() {
        Connection connexion = Connexion.getConnexion().getConexion();
        this.plantaDAO = new PlantaDAOImpl(connexion);
    }

    public static ServicioPlantaImpl getServicioPlanta() {
        if (servicioPlanta == null) {
            servicioPlanta = new ServicioPlantaImpl();
        }
        return servicioPlanta;
    }

    @Override
    public List<Planta> listarPlantas() {
        return plantaDAO.findAll();
    }

    @Override
    public boolean save(Planta planta) {
        if (!planta.getCodigo().matches(codigoPattern)){
            return false;
        }
        return plantaDAO.save(planta);
    }

    @Override
    public void delete(String codigo) {
        plantaDAO.delete(codigo);
    }

    @Override
    public void update(Planta planta) {
        plantaDAO.update(planta);
    }
}
