package org.dwes.servicio;

import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;
import org.dwes.repositorio.PersonaDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.util.List;

public class ServicioPersonaImpl implements ServicioPersona{

    private static ServicioPersonaImpl servicioPersona;
    private final PersonaDAOImpl personaDAO;

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
        return personaDAO.save(persona);
    }

    @Override
    public Credenciales checkForAdmin() {
        if (personaDAO.findByEmail("admin@admin.com").getEmail() == null){
            Persona admin = new Persona(0L,"admin","admin@admin.com");
            personaDAO.save(admin);
            Persona persona = findByEmail("admin@admin.com");
            Credenciales credenciales = new Credenciales();
            credenciales.setUsuario("admin");
            credenciales.setPassword("admin");
            credenciales.setFk_persona(persona);

            return credenciales;
        }
        return null;
    }

    public boolean checkName(String name){
        String namePattern = "^[A-z]+$";
        return !name.matches(namePattern);
    }

    public boolean checkEmail(String email){
        Persona emailCheck = personaDAO.findByEmail(email);
        String emailPattern = "^[\\w]+@[A-Za-z0-9-]+\\.(com|org|es)$";
        return email.matches(emailPattern) && email.equals(emailCheck.getEmail());
    }
}
