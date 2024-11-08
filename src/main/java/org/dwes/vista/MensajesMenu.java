package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Persona;
import org.dwes.util.Validacion;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.dwes.vista.MainMenu.activeUser_id;
import static org.dwes.vista.MainMenu.activeUser_username;

public class MensajesMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);
    PlantasMenu plantasMenu;

    private final Controlador controlador;

    public MensajesMenu() {
        this.controlador = Controlador.getControlador();
        this.plantasMenu = new PlantasMenu();
    }

    /**
     * Menu presentado a un perfil de usuario
     */
    public void menuEjemplaresUser(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario activo: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Hacer anotacion a un ejemplar");
            System.out.println("\t\t\t2 - Listar mensajes");
            System.out.println("\t\t\t3 - Listar mensajes por persona");
            System.out.println("\t\t\t4 - Listar mensajes por rango de fechas");
            System.out.println("\t\t\t5 - Listar por tipo de planta");
            System.out.println("\t\t\t9 - Cerrar Sesion");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        saveMensaje();
                        break;
                    case 2:
                        spacer();
                        findAll();
                        break;
                    case 3:
                        spacer();
                        findAllByPersona();
                        break;
                    case 4:
                        spacer();
                        findBetweenDates();
                        break;
                    case 5:
                        spacer();
                        findAllByPlanta();
                        break;
                    case 9:
                        spacer();
                        System.out.println("Cerrando sesion...");
                        on = false;
                        break;
                    default:
                        spacer();
                        System.err.println("Opción no válida. Por favor, introduzca alguna opción válida de las presentadas.");
                        break;
                }
            } catch (InputMismatchException e){
                spacer();
                System.err.println("Dato introducido no válido. Por favor, introduce una opcion valida.");
                sc.next();
            }
        } while (on);
    }

    private void saveMensaje() {
        List<Ejemplar> ejemplares = controlador.getServicioEjemplar().findAll();

        if (!ejemplares.isEmpty()){
            System.out.println("¿Sobre que ejemplar quieres hacer una anotacion?");

            System.out.println("----------------------------------------------------------------------");
            for (Ejemplar ejemplar : ejemplares){
                System.out.println(ejemplar.toString());
            }
            System.out.println("----------------------------------------------------------------------");

            Long id = null;
            do {
                try{
                    id = sc.nextLong();
                } catch (InputMismatchException inputMismatchException){
                    System.err.println("El Id introducido no es un numero.");
                }
                if (controlador.getServicioEjemplar().findById(id) == null && id == null){
                    System.err.println("Dato introducio no valido.");
                }
            } while (controlador.getServicioEjemplar().findById(id) == null && id == null);

            System.out.println("¿Qué anotación quieres hacer?");
            String txt = sc.next();

            Mensaje mensaje = new Mensaje();
            mensaje.setEjemplar(controlador.getServicioEjemplar().findById(id));
            mensaje.setPersona(controlador.getServicioPersona().findById(activeUser_id));
            mensaje.setMensaje(txt);
            mensaje.setFechaHora(LocalDateTime.now());

            if (controlador.getServicioMensaje().save(mensaje)){
                System.out.println("Anotacion creada con exito.");
            } else {
                System.err.println("Error creando el mensaje.");
            }
        } else {
            System.err.println("Todavia no hay ejemplares en el sistema.");
        }
    }

    private void findAll() {
        List<Mensaje> mensajes = controlador.getServicioMensaje().findAll();

        System.out.println("----------------------------------------------------------------------");
        if (!mensajes.isEmpty()){
            for (Mensaje mensaje : mensajes){
                System.out.println(mensaje.toString());
            }
        } else {
            System.err.println("No hay ningun mensaje en el sistema");
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void findAllByPersona() {
        Long id = null;
        Persona persona = null;
        
        List<Persona> personas = controlador.getServicioPersona().findAll();
        if (!personas.isEmpty()){
            for (Persona persona1 : personas){
                System.out.println("ID: " + persona1.getId() + " | Nombre: " + persona1.getEmail());
            }

            do {
                System.out.println("¿De que persona te gustaria ver los mensajes?");
                try {
                    id = sc.nextLong();
                    persona = controlador.getServicioPersona().findById(id);
                    if (!Objects.equals(persona.getId(), id)){
                        System.err.println("Esa persona no existe.");
                    }
                } catch (IllegalArgumentException illegalArgumentException){
                    System.err.println("No has introducido una id de usuario. Valor NO valido --> " + id);
                }
            } while (!Objects.equals(Objects.requireNonNull(persona).getId(), id));

            List<Mensaje> mensajes = controlador.getServicioMensaje().findByPersona(id);
            if (!mensajes.isEmpty()){
                for (Mensaje mensaje : mensajes){
                    System.out.println("Mensaje: " + mensaje.getMensaje() + " | Fecha y Hora: " + mensaje.getFechaHora());
                }
            } else {
                System.err.println("Esa persona no tiene ningun mensaje.");
            }
        } else {
            System.err.println("No hay personas registradas en el sistema");
        }
    }

    private void findBetweenDates() {
        LocalDateTime localDateTime1;
        LocalDateTime localDateTime2;
        System.out.println("Introduce primero la fecha mas antigua y luego la mas nueva");
        do {
            localDateTime1 = Validacion.pedirFecha();
            localDateTime2 = Validacion.pedirFecha();
        } while (((localDateTime1 == null) || (localDateTime2 == null)) && Objects.requireNonNull(localDateTime2).isAfter(localDateTime1));

        List<Mensaje> mensajes = controlador.getServicioMensaje().findBetweenDateTime(localDateTime1,localDateTime2);
        if (!mensajes.isEmpty()){
            for (Mensaje mensaje : mensajes){
                System.out.println("Mensaje: " + mensaje.getMensaje() + " | Fecha y Hora: " + mensaje.getFechaHora());
            }
        } else {
            System.err.println("No se han encontrado mensajes en esas fechas.");
        }
    }

    private void findAllByPlanta() {
        plantasMenu.listadoPlantas();

        System.out.println("¿Sobre que planta te gustaria ver los mensajes?");
        String codigo = sc.next();

        List<Ejemplar> ejemplares = controlador.getServicioEjemplar().findByFkPlanta(codigo);
        for (Ejemplar ejemplar : ejemplares){
            System.out.println("\nMensajes del emjemplar con nombre: " + ejemplar.getNombre());
            List<Mensaje> mensajes = controlador.getServicioMensaje().findByEjemplar(ejemplar.getId());
            System.out.println(mensajes.toString());
        }
    }

    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }
}
