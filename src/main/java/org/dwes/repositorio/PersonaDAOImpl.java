package org.dwes.repositorio;

import org.dwes.modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOImpl implements PersonaDAO{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PersonaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Da un listado de todas las personas de la base de datos
     *
     * @return lista de objetos Persona
     */
    @Override
    public List<Persona> findAll() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                Persona persona = new Persona(id,nombre,email);
                personas.add(persona);
            }

        } catch (SQLException e) {
            System.err.println("Error haciendo el listado de personas --> " + e.getMessage());
        }

        return personas;
    }

    /**
     * Devuelve una persona de la base de datos por codigo
     *
     * @param id el codigo de la persona a buscar
     * @return si se encuentra una Persona la devuelve, si no devuelve un objeto con vacio.
     */
    @Override
    public Persona findById(Long id) {
        Persona persona = new Persona();
        String sql = "SELECT * FROM persona WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long identificador = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                persona.setId(identificador);
                persona.setNombre(nombre);
                persona.setEmail(email);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando personas por id --> " + e.getMessage());
        }

        return persona;
    }

    /**
     * Devuelve una persona de la base de datos por codigo
     *
     * @param mail el emial de la persona a buscar
     * @return si se encuentra una Persona la devuelve, si no devuelve un objeto vacio.
     */
    @Override
    public Persona findByEmail(String mail) {
        Persona persona = new Persona();
        String sql = "SELECT * FROM persona WHERE email = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long identificador = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                persona.setId(identificador);
                persona.setNombre(nombre);
                persona.setEmail(email);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando personas por email --> " + e.getMessage());
        }

        return persona;
    }

    /**
     * Guarda una Persona en la base de datos
     *
     * @param persona la persona a guardar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean save(Persona persona) {
        String sql = "INSERT INTO persona (nombre, email) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getEmail());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar la persona --> " + e.getMessage());
            return false;
        }
    }
}
