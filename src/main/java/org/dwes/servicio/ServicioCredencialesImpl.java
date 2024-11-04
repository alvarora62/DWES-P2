package org.dwes.servicio;

import org.dwes.modelo.Credenciales;
import org.dwes.repositorio.CredencialesDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;

public class ServicioCredencialesImpl implements ServicioCredenciales{

    private static ServicioCredencialesImpl servicioCredenciales;
    private CredencialesDAOImpl credencialesDAO;
    private final String passwdPattern = "^(?=.*[A-Z])(?=.*\\\\d)(?=.*[!@#$%^&*(),.?\\\":{}|<>])[A-Za-z\\\\d!@#$%^&*(),.?\\\":{}|<>]{8,}$";

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
    public boolean save(Credenciales credenciales) {
        Credenciales id = credencialesDAO.findByUsuario(credenciales.getUsuario());
        if (id.getId() != null){
            return false;
        }

        if (!credenciales.getPassword().matches(passwdPattern)){
            return false;
        }
        return credencialesDAO.save(credenciales);
    }

    /**
     * Metodo para realizar el inicio de sesion en la aplicacion
     *
     * @param username - usuario a logear
     * @param password - contraseña del usuario
     * @return -1 si el login ha falado, 0 si es un empleado y 1 si es administrador
     */
    @Override
    public int login(String username, String password){
        Credenciales credenciales = credencialesDAO.findByUsuario(username);
        if (username.equals("admin") && password.equals("admin")){
            return 1;
        }

        if (username.equals(credenciales.getUsuario()) && password.equals(credenciales.getPassword())){
            return 0;
        }
        return -1;
    }
}
