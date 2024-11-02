package org.dwes.servicio;

import org.dwes.modelo.Persona;
import org.dwes.repositorio.PersonaDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPersonaImpl implements ServicioPersona{

    private static ServicioPersonaImpl servicioPersona;
    private PersonaDAOImpl personaDAO;

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
    public boolean save(Persona persona) {
        if (!persona.getEmail().matches("[A-z][0-9]+@+[A-z][0-9]+(com|es|org)") && personaDAO.findByEmail(persona.getEmail()) != null){
            return false;
        }

        return personaDAO.save(persona);
    }
}
