package org.dwes.repositorio;

import org.dwes.modelo.Planta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlantaDAOImpl implements PlantaDAO {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PlantaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Da un listado de todas las plantas de la base de datos
     *
     * @return lista de objetos Planta
     */
    @Override
    public List<Planta> findAll() {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT * FROM planta";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nombreComun = resultSet.getString("nombreComun");
                String nombreCientifico = resultSet.getString("nombreCientifico");
                Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
                plantas.add(planta);
            }

        } catch (SQLException e) {
            System.err.println("Error haciendo el listado de plantas --> " + e.getMessage());
            e.printStackTrace();
        }

        return plantas;
    }

    /**
     * Devuelve una planta de la base de datos por codigo
     *
     * @param id el codigo de la planta a buscar
     * @return si se encuentra una Planta la devuelve, si no devuelve un objeto vacio.
     */
    @Override
    public Planta findById(String id) {
        Planta planta = new Planta();
        String sql = "SELECT * FROM planta WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nombreComun = resultSet.getString("nombreComun");
                String nombreCientifico = resultSet.getString("nombreCientifico");
                planta.setCodigo(codigo);
                planta.setNombreComun(nombreComun);
                planta.setNombreCientifico(nombreCientifico);
            }
        } catch (SQLException e) {
            System.err.println("Error buscando plantas por codigo --> " + e.getMessage());
            e.printStackTrace();
        }
        return planta;
    }

    /**
     * Guarda una planta en la base de datos
     *
     * @param planta la planta a guardar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean save(Planta planta) {
        String sql = "INSERT INTO planta (codigo, nombreComun, nombreCientifico) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planta.getCodigo());
            preparedStatement.setString(2, planta.getNombreComun());
            preparedStatement.setString(3, planta.getNombreCientifico());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar la planta --> " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una planta de la base de datos
     *
     * @param codigo la planta a eliminar
     * @return devuelve true si la operacion se hace y false si algo falla
     */
    @Override
    public boolean delete(String codigo) {
        String sql = "DELETE FROM planta WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error eliminando la planta --> " + e.getMessage());
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
        String sql = "UPDATE planta SET nombreComun = ?, nombreCientifico = ? WHERE codigo = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, planta.getNombreComun());
            preparedStatement.setString(2, planta.getNombreCientifico());
            preparedStatement.setString(3, planta.getCodigo());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error actualizando la planta --> " + e.getMessage());
            return false;
        }
    }
}
