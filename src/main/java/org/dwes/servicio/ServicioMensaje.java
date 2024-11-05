package org.dwes.servicio;

import org.dwes.modelo.Mensaje;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioMensaje {

    List<Mensaje> findAll();
    List<Mensaje> findByPersona(Long id);
    List<Mensaje> findByPlanta(Long id);
    List<Mensaje> findBetweenDateTime(LocalDateTime firstLocalDateTime, LocalDateTime secondLocalDateTime);
    boolean save(Mensaje mensaje);
}
