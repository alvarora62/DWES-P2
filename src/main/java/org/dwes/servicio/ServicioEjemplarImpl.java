package org.dwes.servicio;

import org.dwes.modelo.Ejemplar;
import org.dwes.repositorio.EjemplarDAOImpl;
import org.dwes.repositorio.MensajeDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioEjemplarImpl implements ServicioEjemplar{

    private static ServicioEjemplarImpl servicioEjemplar;
    private EjemplarDAOImpl ejemplarDAO;

    private ServicioEjemplarImpl() {
        Connection connexion = Connexion.getConnexion().getConexion();
        this.ejemplarDAO = new EjemplarDAOImpl(connexion);
    }

    public static ServicioEjemplarImpl getServicioEjemplar() {
        if (servicioEjemplar == null) {
            servicioEjemplar = new ServicioEjemplarImpl();
        }
        return servicioEjemplar;
    }

    @Override
    public List<Ejemplar> findAll() {
        return ejemplarDAO.findAll();
    }

    @Override
    public List<Ejemplar> findByFkPlanta(Long id) {
        return ejemplarDAO.findByFkPlanta(id);
    }

    @Override
    public boolean save(Ejemplar ejemplar) {
        return ejemplarDAO.save(ejemplar);
    }
}
