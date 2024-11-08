package org.dwes.servicio;

import org.dwes.modelo.Credenciales;
import org.dwes.repositorio.CredencialesDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;

public class ServicioCredencialesImpl implements ServicioCredenciales{

    private static ServicioCredencialesImpl servicioCredenciales;
    private final CredencialesDAOImpl credencialesDAO;

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
    public Credenciales findByUsername(String username) {
        return credencialesDAO.findByUsuario(username);
    }

    @Override
    public boolean save(Credenciales credenciales) {
        return credencialesDAO.save(credenciales);
    }

    public boolean chechUsername(String username){
        return credencialesDAO.findByUsuario(username) == null;
    }

    public boolean checkPassword(String password){
        if (password.length() < 8)
            return false;

        if (!password.matches(".*[A-Z].*"))
            return false;

        if (!password.matches(".*[0-9].*"))
            return false;

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~].*"))
            return false;

        return true;
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

        if (credenciales == null){
            return -1;
        }

        if (username.equals(credenciales.getUsuario()) && password.equals(credenciales.getPassword())){
            return 0;
        }
        return -1;
    }
}
