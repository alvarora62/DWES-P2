package org.dwes.servicio;

import org.dwes.modelo.Planta;
import org.dwes.repositorio.PlantaDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPlantaImpl implements ServicioPlanta {

    private static ServicioPlantaImpl servicioPlanta;
    private PlantaDAOImpl plantaDAO;
    private final String codigoPattern = "^[A-Za-z]+$";

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
    public Planta findByCodigo(String codigo) {
        return plantaDAO.findById(codigo);
    }


    @Override
    public boolean save(Planta planta) {
        if (!planta.getCodigo().toUpperCase().matches(codigoPattern)){
            return false;
        }
        planta.setCodigo(planta.getCodigo().toUpperCase());
        return plantaDAO.save(planta);
    }

    @Override
    public void delete(String codigo) {
        plantaDAO.delete(codigo);
    }

    @Override
    public boolean update(Planta planta) {
        return plantaDAO.update(planta);
    }
}
