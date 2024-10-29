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
     * @return lista de objetos Planta
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
            System.err.println("Error haciendo el listado de plantas --> " + e.getMessage());
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
                persona = new Persona(id, nombre, email);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando personas por codigo --> " + e.getMessage());
            e.printStackTrace();
        }

        return persona;
    }

    /**
     * Guarda una planta en la base de datos
     *
     * @param planta la planta a guardar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean save(Planta planta) {
        String sql = "INSERT INTO plantas (codigo, nombre_comun, nombre_cientifico) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planta.getCodigo());
            preparedStatement.setString(2, planta.getNombreComun());
            preparedStatement.setString(3, planta.getNombreCientifico());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar la planta --> " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una planta de la base de datos
     *
     * @param planta la planta a eliminar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean delete(Planta planta) {
        String sql = "DELETE FROM plantas WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planta.getCodigo());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error eliminando la planta --> " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza una panta existente en la base de datos
     *
     * @param planta la planta para actualizar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean update(Planta planta) {
        String sql = "UPDATE plantas SET nombre_comun = ?, nombre_cientifico = ? WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planta.getNombreComun());
            preparedStatement.setString(2, planta.getNombreCientifico());
            preparedStatement.setString(3, planta.getCodigo());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error actualizando la planta --> " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
