package org.dwes.servicio;

import org.dwes.modelo.Persona;
import org.dwes.repositorio.PersonaDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPersonaImpl implements ServicioPersona{

    private static ServicioPersonaImpl servicioPersona;
    private PersonaDAOImpl personaDAO;
    private final String namePattern = "[A-Za-z]";
    private final String emailPattern = "^[\\w]+@[A-Za-z0-9-]+\\.(com|org|es)$";

    private ServicioPersonaImpl() {
        Connection connexion = Connexion.getConnexion().getConexion();
        this.personaDAO = new PersonaDAOImpl(connexion);
    }

    public static ServicioPersonaImpl getServicioPersona() {
        if (servicioPersona == null) {
            servicioPersona = new ServicioPersonaImpl();
        }
        return servicioPersona;
    }

    @Override
    public List<Persona> findAll() {
        return personaDAO.findAll();
    }

    @Override
    public Persona findById(Long id) {
        return personaDAO.findById(id);
    }

    @Override
    public Persona findByEmail(String email){
        return personaDAO.findByEmail(email);
    }

    @Override
    public boolean save(Persona persona) {
        if (!persona.getNombre().matches(namePattern)){
            return false;
        }
        Persona emailCheck = personaDAO.findByEmail(persona.getEmail());
        if (!persona.getEmail().matches(emailPattern) && !persona.getEmail().equals(emailCheck.getEmail())){
            return false;
        }
        return personaDAO.save(persona);
    }
}
