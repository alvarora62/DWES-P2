package org.dwes.repositorio;

import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAOImpl implements MensajeDAO {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MensajeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Devuelve un listado de todos los mensajes de la base de datos.
     *
     * @return lista de objetos Mensaje
     */
    @Override
    public List<Mensaje> findAll() {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM mensaje";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                LocalDateTime fecha = resultSet.getObject("fechahora", LocalDateTime.class);
                String mensaje = resultSet.getString("mensaje");
                Long fk_personaMensaje = resultSet.getLong("fk_personaMensaje");
                Persona persona = new Persona();
                persona.setId(fk_personaMensaje);
                Long fk_ejemplarMensaje = resultSet.getLong("fk_ejemplarMensaje");
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(fk_ejemplarMensaje);

                Mensaje respuesta = new Mensaje();
                respuesta.setId(id);
                respuesta.setFechaHora(fecha);
                respuesta.setMensaje(mensaje);
                respuesta.setPersona(persona);
                respuesta.setEjemplar(ejemplar);
                mensajes.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar mensajes --> " + e.getMessage());
        }

        return mensajes;
    }

    /**
     * Devuelve una lista de mensajes de una persona específica.
     *
     * @param id el ID de la persona a filtrar
     * @return lista de objetos Mensaje para la persona
     */
    @Override
    public List<Mensaje> findByPersona(Long id) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM mensaje WHERE fk_personaMensaje = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id_mensaje = resultSet.getLong("id");
                LocalDateTime fecha = resultSet.getObject("fechahora", LocalDateTime.class);
                String mensaje = resultSet.getString("mensaje");
                Long fk_personaMensaje = resultSet.getLong("fk_personaMensaje");
                Persona persona = new Persona();
                persona.setId(fk_personaMensaje);
                Long fk_ejemplarMensaje = resultSet.getLong("fk_ejemplarMensaje");
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(fk_ejemplarMensaje);

                Mensaje respuesta = new Mensaje();
                respuesta.setId(id_mensaje);
                respuesta.setFechaHora(fecha);
                respuesta.setMensaje(mensaje);
                respuesta.setPersona(persona);
                respuesta.setEjemplar(ejemplar);
                mensajes.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando mensajes por persona --> " + e.getMessage());
        }

        return mensajes;
    }

    /**
     * Devuelve una lista de mensajes de una planta específica.
     *
     * @param id el ID de la planta a filtrar
     * @return lista de objetos Mensaje para la planta
     */
    @Override
    public List<Mensaje> findByEjemplar(Long id) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM mensaje WHERE fk_ejemplarMensaje = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id_mensaje = resultSet.getLong("id");
                LocalDateTime fecha = resultSet.getObject("fechahora", LocalDateTime.class);
                String mensaje = resultSet.getString("mensaje");
                Long fk_personaMensaje = resultSet.getLong("fk_personaMensaje");
                Persona persona = new Persona();
                persona.setId(fk_personaMensaje);
                Long fk_ejemplarMensaje = resultSet.getLong("fk_ejemplarMensaje");
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(fk_ejemplarMensaje);

                Mensaje respuesta = new Mensaje();
                respuesta.setId(id_mensaje);
                respuesta.setFechaHora(fecha);
                respuesta.setMensaje(mensaje);
                respuesta.setPersona(persona);
                respuesta.setEjemplar(ejemplar);
                mensajes.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando mensajes por planta --> " + e.getMessage());
        }

        return mensajes;
    }

    /**
     * Devuelve una lista de mensajes en un rango específico de fechas.
     *
     * @param firstLocalDateTime  la fecha y hora de inicio del rango
     * @param secondLocalDateTime la fecha y hora de fin del rango
     * @return lista de objetos Mensaje dentro del rango de fechas
     */
    @Override
    public List<Mensaje> findBetweenDateTime(LocalDateTime firstLocalDateTime, LocalDateTime secondLocalDateTime) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT * FROM mensaje WHERE fechahora BETWEEN ? AND ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, firstLocalDateTime);
            preparedStatement.setObject(2, secondLocalDateTime);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                LocalDateTime fecha = resultSet.getObject("fechahora", LocalDateTime.class);
                String mensaje = resultSet.getString("mensaje");
                Long fk_personaMensaje = resultSet.getLong("fk_personaMensaje");
                Persona persona = new Persona();
                persona.setId(fk_personaMensaje);
                Long fk_ejemplarMensaje = resultSet.getLong("fk_ejemplarMensaje");
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(fk_ejemplarMensaje);

                Mensaje respuesta = new Mensaje();
                respuesta.setId(id);
                respuesta.setFechaHora(fecha);
                respuesta.setMensaje(mensaje);
                respuesta.setPersona(persona);
                respuesta.setEjemplar(ejemplar);
                mensajes.add(respuesta);
            }

        } catch (SQLException e) {
            System.err.println("Error buscando mensajes entre fechas --> " + e.getMessage());
        }

        return mensajes;
    }

    /**
     * Guarda un mensaje en la base de datos.
     *
     * @param mensaje el objeto Mensaje a guardar
     * @return true si guarda el mensaje, false si no
     */
    @Override
    public boolean save(Mensaje mensaje) {
        String sql = "INSERT INTO mensaje (fechahora, mensaje, fk_personaMensaje, fk_ejemplarMensaje) VALUES (?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, mensaje.getFechaHora());
            preparedStatement.setString(2, mensaje.getMensaje());
            preparedStatement.setLong(3, mensaje.getPersona().getId());
            preparedStatement.setLong(4, mensaje.getEjemplar().getId());
            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar el mensaje --> " + e.getMessage());
            return false;
        }
    }
}
