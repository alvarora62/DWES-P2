package org.dwes.controlador;

import org.dwes.servicio.*;

public class Controlador {

    private static Controlador controlador;

    private final ServicioCredencialesImpl servicioCredenciales;
    private final ServicioPersonaImpl servicioPersona;
    private final ServicioPlantaImpl servicioPlanta;
    private final ServicioEjemplarImpl servicioEjemplar;
    private final ServicioMensajeImpl servicioMensaje;

    private Controlador() {
        this.servicioCredenciales = ServicioCredencialesImpl.getServicioCredenciales();
        this.servicioPersona = ServicioPersonaImpl.getServicioPersona();
        this.servicioPlanta = ServicioPlantaImpl.getServicioPlanta();
        this.servicioEjemplar = ServicioEjemplarImpl.getServicioEjemplar();
        this.servicioMensaje = ServicioMensajeImpl.getServicioMensaje();
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

    public ServicioEjemplarImpl getServicioEjemplar() {
        return servicioEjemplar;
    }

    public ServicioMensajeImpl getServicioMensaje() {
        return servicioMensaje;
    }
}
