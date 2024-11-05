package org.dwes.servicio;

import org.dwes.modelo.Mensaje;
import org.dwes.repositorio.MensajeDAOImpl;
import org.dwes.util.Connexion;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class ServicioMensajeImpl implements ServicioMensaje{

    private static ServicioMensajeImpl servicioMensaje;
    private MensajeDAOImpl mensajeDAO;

    private ServicioMensajeImpl() {
        Connection connexion = Connexion.getConnexion().getConexion();
        this.mensajeDAO = new MensajeDAOImpl(connexion);
    }

    public static ServicioMensajeImpl getServicioMensaje() {
        if (servicioMensaje == null) {
            servicioMensaje = new ServicioMensajeImpl();
        }
        return servicioMensaje;
    }

    @Override
    public List<Mensaje> findAll() {
        return mensajeDAO.findAll();
    }

    @Override
    public List<Mensaje> findByPersona(Long id) {
        return mensajeDAO.findByPersona(id);
    }

    @Override
    public List<Mensaje> findByEjemplar(Long id) {
        return mensajeDAO.findByEjemplar(id);
    }

    @Override
    public List<Mensaje> findBetweenDateTime(LocalDateTime firstLocalDateTime, LocalDateTime secondLocalDateTime) {
        return mensajeDAO.findBetweenDateTime(firstLocalDateTime,secondLocalDateTime);
    }

    @Override
    public boolean save(Mensaje mensaje) {
        return mensajeDAO.save(mensaje);
    }
}
