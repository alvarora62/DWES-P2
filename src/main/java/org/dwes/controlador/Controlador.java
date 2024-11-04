package org.dwes.controlador;

import org.dwes.servicio.ServicioCredencialesImpl;
import org.dwes.servicio.ServicioPersonaImpl;
import org.dwes.servicio.ServicioPlantaImpl;

public class Controlador {

    private static Controlador controlador;

    private final ServicioCredencialesImpl servicioCredenciales;
    private final ServicioPersonaImpl servicioPersona;
    private final ServicioPlantaImpl servicioPlanta;

    private Controlador() {
        this.servicioCredenciales = ServicioCredencialesImpl.getServicioCredenciales();
        this.servicioPersona = ServicioPersonaImpl.getServicioPersona();
        this.servicioPlanta = ServicioPlantaImpl.getServicioPlanta();
    }

    public static Controlador getControlador() {
        if (controlador == null) {
            controlador = new Controlador();
        }
        return controlador;
    }

    public ServicioCredencialesImpl getServicioCredenciales() {
        return servicioCredenciales;
    }

    public ServicioPersonaImpl getServicioPersona() {
        return servicioPersona;
    }

    public ServicioPlantaImpl getServicioPlanta() {
        return servicioPlanta;
    }
}
