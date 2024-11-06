package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Persona;

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

    private final Controlador controlador;

    public MensajesMenu() {
        this.controlador = Controlador.getControlador();
    }

    /**
     * Menu presentado a un perfil de usuario
     */
    public void menuEjemplaresUser(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario activo: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Hacer anotacion a un ejemplar");
            System.out.println("\t\t\t2 - Listar mensajes (NO IMPLEMENTADO)");
            System.out.println("\t\t\t2 - Listar mensajes por persona (NO IMPLEMENTADO)");
            System.out.println("\t\t\t2 - Listar mensajes por rango de fechas(NO IMPLEMENTADO)");
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

        if (!mensajes.isEmpty()){
            for (Mensaje mensaje : mensajes){
                System.out.println(mensaje.toString());
            }
        } else {
            System.err.println("No hay ningun mensaje en el sistema");
        }
    }

    private void findAllByPersona() {
        Long id;
        Persona persona;

        List<Persona> personas = controlador.getServicioPersona().findAll();
        if (!personas.isEmpty()){
            for (Persona persona1 : personas){
                System.out.println("ID: " + persona1.getId() + " | Nombre: " + persona1.getEmail());
            }

            do {
                System.out.println("¿De que persona te gustaria ver los mensajes?");
                id = sc.nextLong();

                persona = controlador.getServicioPersona().findById(id);
                if (!Objects.equals(persona.getId(), id)){
                    System.err.println("Esa persona no existe.");
                }
            } while (!Objects.equals(persona.getId(), id));

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

    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }
}
