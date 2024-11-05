package org.dwes.repositorio;

import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Persona;

import java.time.LocalDateTime;
import java.util.List;

public interface MensajeDAO {

    List<Mensaje> findAll();
    List<Mensaje> findByPersona(Long id);
    List<Mensaje> findByPlanta(Long id);
    List<Mensaje> findBetweenDateTime(LocalDateTime firstLocalDateTime, LocalDateTime secondLocalDateTime);
    boolean save(Mensaje mensaje);
}
