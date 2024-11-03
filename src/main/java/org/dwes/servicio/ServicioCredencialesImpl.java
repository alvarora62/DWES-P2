package org.dwes.servicio;

import org.dwes.modelo.Credenciales;
import org.dwes.repositorio.CredencialesDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;

public class ServicioCredencialesImpl implements ServicioCredenciales{

    private static ServicioCredencialesImpl servicioCredenciales;
    private CredencialesDAOImpl credencialesDAO;

    private ServicioCredencialesImpl() {
        Connection connexion = Connexion.getConnexion().getConexion();
        this.credencialesDAO = new CredencialesDAOImpl(connexion);
    }

    public static ServicioCredencialesImpl getServicioCredenciales() {
        if (servicioCredenciales == null) {
            servicioCredenciales = new ServicioCredencialesImpl();
        }
        return servicioCredenciales;
    }


    @Override
    public Credenciales findByUsuario(String user) {
        return credencialesDAO.findByUsuario(user);
    }

    @Override
    public boolean save(Credenciales credenciales) {
        if (credencialesDAO.findByUsuario(credenciales.getUsuario()).getId() != 0){
            return false;
        }
        return credencialesDAO.save(credenciales);
    }
}
