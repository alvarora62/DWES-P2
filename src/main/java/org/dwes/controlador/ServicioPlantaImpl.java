package org.dwes.controlador;

import org.dwes.dao.PlantaDAOImpl;
import org.dwes.modelo.Planta;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPlantaImpl implements ServicioPlanta {

    private static ServicioPlantaImpl servicioPlanta;
    private PlantaDAOImpl plantaDAO;

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
        List<Planta> plantaList = plantaDAO.findAll();

        if (plantaList.isEmpty()) {
            System.out.println("No hay plantas registradas en el sistema");
        }

        return plantaList;
    }

    @Override
    public void save(Planta planta) {
        if (planta != null) {
            if (!plantaDAO.save(planta)) {
                System.err.println("Error guardando la planta");
            }
        } else {
            System.err.println("Planta no puede ser nulo");
        }
    }

    @Override
    public void delete(String codigo) {
        if (codigo != null && !codigo.isEmpty()) {
            if (!plantaDAO.delete(codigo)) {
                System.err.println("Error eliminando la planta con código: " + codigo);
            }
        } else {
            System.err.println("El código no puede ser nulo o estar vacío");
        }
    }

    @Override
    public void update(Planta planta) {
        if (planta != null) {
            if (!plantaDAO.update(planta)) {
                throw new RuntimeException("Error actualizando la planta");
            }
        } else {
            throw new IllegalArgumentException("Planta no puede ser nulo");
        }
    }
}
