package org.dwes.repositorio;

import org.dwes.modelo.Persona;
import org.dwes.modelo.Planta;
import org.dwes.util.Connexion;

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
     * Da un listado de todas las plantas de la base de datos
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
            e.printStackTrace();
        }

        return personas;
    }

    /**
     * Devuelve una persona de la base de datos por codigo
     *
     * @param id el codigo de la persona a buscar
     * @return si se encuentra una Persona la devuelve, si no devuelve nulo.
     */
    @Override
    public Persona findById(Long id) {
        Persona persona = null;
        String sql = "SELECT * FROM persona WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long identificador = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                persona = new Persona(identificador, nombre, email);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando personas por id --> " + e.getMessage());
            e.printStackTrace();
        }

        return persona;
    }

    /**
     * Guarda una Persona en la base de datos
     *
     * @param persona la planta a guardar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean save(Persona persona) {
        String sql = "INSERT INTO persona (id, nombre, email) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, persona.getId());
            preparedStatement.setString(2, persona.getNombre());
            preparedStatement.setString(3, persona.getEmail());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar la persona --> " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}