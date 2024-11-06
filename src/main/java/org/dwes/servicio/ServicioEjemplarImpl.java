package org.dwes.servicio;

import org.dwes.modelo.Ejemplar;
import org.dwes.repositorio.EjemplarDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioEjemplarImpl implements ServicioEjemplar{

    private static ServicioEjemplarImpl servicioEjemplar;
    private final EjemplarDAOImpl ejemplarDAO;

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
    public Ejemplar findById(Long id){
        return ejemplarDAO.findById(id);
    }

    @Override
    public List<Ejemplar> findByFkPlanta(String codigo) {
        return ejemplarDAO.findByFkPlanta(codigo);
    }

    @Override
    public boolean save(Ejemplar ejemplar) {
        if (!ejemplarDAO.save(ejemplar)){
            return false;
        }

        List<Ejemplar> ejemplars = ejemplarDAO.findAll();
        Ejemplar e = ejemplars.get(ejemplars.size() - 1);
        ejemplar.setId(e.getId());
        ejemplar.setNombre(e.getPlanta().getCodigo() + "-" + e.getId());

        return ejemplarDAO.update(ejemplar);
    }

    @Override
    public boolean update(Ejemplar ejemplar) {
        return ejemplarDAO.update(ejemplar);
    }
}
