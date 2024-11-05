package org.dwes.repositorio;

import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Planta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjemplarDAOImpl implements EjemplarDAO{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EjemplarDAOImpl(Connection connection) {
        this.connection = connection;
    }


    /**
     * Devuelve un listado de todos los ejemplaers de la base de datos.
     *
     * @return lista de objetos Ejemplar
     */
    @Override
    public List<Ejemplar> findAll() {
        List<Ejemplar> ejemplares = new ArrayList<>();
        String sql = "SELECT * FROM ejemplar";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String fkPlanta = resultSet.getString("fk_planta");

                Ejemplar respuesta = new Ejemplar();
                respuesta.setId(id);
                respuesta.setNombre(nombre);
                Planta planta = new Planta();
                planta.setCodigo(fkPlanta);
                respuesta.setPlanta(planta);

                ejemplares.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ejemplares --> " + e.getMessage());
        }

        return ejemplares;
    }

    /**
     * Devuelve una lista de ejemplares de una planta específica.
     *
     * @param id el ID de la planta a filtrar
     * @return lista de objetos Ejemplar para la planta indicada
     */
    @Override
    public List<Ejemplar> findByFkPlanta(Long id) {
        List<Ejemplar> ejemplares = new ArrayList<>();
        String sql = "SELECT * FROM ejemplar WHERE fk_planta = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id_ejemplar = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String fkPlanta = resultSet.getString("fk_planta");

                Ejemplar respuesta = new Ejemplar();
                respuesta.setId(id_ejemplar);
                respuesta.setNombre(nombre);
                Planta planta = new Planta();
                planta.setCodigo(fkPlanta);
                respuesta.setPlanta(planta);

                ejemplares.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ejemplares --> " + e.getMessage());
        }

        return ejemplares;
    }

    /**
     * Guarda un ejemplar en la base de datos.
     *
     * @param ejemplar el objeto Ejemplar a guardar
     * @return true si guarda el ejemplar, false si no
     */
    @Override
    public boolean save(Ejemplar ejemplar) {
        String sql = "INSERT INTO ejemplar (nombre, fk_planta) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ejemplar.getNombre());
            preparedStatement.setString(2, ejemplar.getPlanta().getCodigo());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar el ejemplar --> " + e.getMessage());
            return false;
        }
    }
}
