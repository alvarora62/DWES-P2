package org.dwes.repositorio;

import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CredencialesDAOImpl implements CredencialesDAO{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CredencialesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Da un listado de todas las credenciales de la base de datos
     *
     * @return lista de objetos credenciales
     */
    @Override
    public List<Credenciales> findAll() {
        List<Credenciales> listaCredenciales = new ArrayList<>();
        String sql = "SELECT * FROM credenciales";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String usuario = resultSet.getString("usuario");
                String password = resultSet.getString("password");
                Persona fk_persona = new Persona(resultSet.getLong("fk_persona"));
                Credenciales credenciales = new Credenciales(id,usuario,password,fk_persona);

                listaCredenciales.add(credenciales);
            }

        } catch (SQLException e) {
            System.err.println("Error haciendo el listado de credenciales --> " + e.getMessage());
            e.printStackTrace();
        }

        return listaCredenciales;
    }

    @Override
    public Credenciales findByUsuario(String user) {
        Credenciales credenciales = new Credenciales();
        String sql = "SELECT * FROM credenciales WHERE usuario = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String usuario = resultSet.getString("usuario");
                String password = resultSet.getString("password");
                Persona fk_persona = new Persona(resultSet.getLong("fk_persona"));
                credenciales.setId(id);
                credenciales.setUsuario(usuario);
                credenciales.setPassword(password);
                credenciales.setFk_persona(fk_persona);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando personas por usuario --> " + e.getMessage());
            e.printStackTrace();
        }

        return credenciales;
    }

    @Override
    public boolean save(Credenciales credenciales) {
        String sql = "INSERT INTO credenciales (id, usuario, password, fk_persona) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, credenciales.getId());
            preparedStatement.setString(2, credenciales.getUsuario());
            preparedStatement.setString(3, credenciales.getPassword());
            preparedStatement.setLong(4, credenciales.getFk_persona().getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar la credencial --> " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
