package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Persona;
import org.dwes.modelo.Planta;

import java.time.LocalDateTime;
import java.util.*;

import static org.dwes.vista.MainMenu.activeUser_id;
import static org.dwes.vista.MainMenu.activeUser_username;

public class EjemplaresMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;
    private final PlantasMenu plantasMenu;

    public EjemplaresMenu() {
        this.controlador = Controlador.getControlador();
        this.plantasMenu = new PlantasMenu();
    }

    /**
     * Menu presentado a un perfil de usuario
     */
    public void menuEjemplaresUser(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero**  [Usuario Activo: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Resgistrar ejemplar");
            System.out.println("\t\t\t2 - Listar ejemplares por Planta");
            System.out.println("\t\t\t3 - Ver mensajes de seguimiento (NO IMPLEMENTADO)");
            System.out.println("\t\t\t9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        saveEjemplar();
                        break;
                    case 2:
                        spacer();
                        listByPlantFK();
                        break;

                    case 3:
                        spacer();
                        listAllMensajeByEjemplarID();
                        break;
                    case 9:
                        spacer();
                        System.out.println("Saliendo de la gestion de ejemplares...");
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

    private void saveEjemplar() {
        System.out.println("Registro de un nuevo ejemplar.\n");

        if (!controlador.getServicioPlanta().listarPlantas().isEmpty()){
            plantasMenu.listadoPlantas();

            Planta planta;
            do {
                System.out.println("¿De qué tipo de planta es el ejemplar nuevo?");
                String fk_planta = sc.next().toUpperCase().trim();
                planta = controlador.getServicioPlanta().findByCodigo(fk_planta);
                if (planta == null)
                    System.err.println("Codigo de planta NO valido");
            } while (planta == null);

            Ejemplar ejemplar = new Ejemplar();
            ejemplar.setPlanta(planta);

            if (controlador.getServicioEjemplar().save(ejemplar)){
                Mensaje mensaje = new Mensaje();
                mensaje.setPersona(controlador.getServicioPersona().findById(activeUser_id));
                mensaje.setEjemplar(ejemplar);
                controlador.getServicioMensaje().mensajeInicial(mensaje);

                System.out.println("Ejemplar añadido con exito");
            } else {
                System.err.println("Error creando el mensaje inicial.");
            }
        } else {
            System.err.println("No hay plantas en el sistema");
        }
    }

    private void listByPlantFK() {
        System.out.println("Estas son las plantas guardadas actualmente en el sistema:");

        if (!controlador.getServicioPlanta().listarPlantas().isEmpty()){
            plantasMenu.listadoPlantas();

            List<String> codigosPlantas = new ArrayList<>();
            String salir = "salir";

            String codigo;
            do {
                System.out.print("\nIngrese el código de la planta para buscar sus ejemplares (usa '" + salir + "' para finalizar la selección): ");
                codigo = sc.next().trim().toUpperCase();
                if (!codigo.equalsIgnoreCase(salir)){
                    if (controlador.getServicioPlanta().findByCodigo(codigo) != null){
                        codigosPlantas.add(codigo);
                    } else {
                        System.err.println("El codigo introducido no existe.");
                    }
                }
            } while (!codigo.equalsIgnoreCase(salir));

            System.out.println("\nEjemplares encontrados:");
            System.out.println("----------------------------------------------------------------------");
            for (String codigoPlanta : codigosPlantas){
                List<Ejemplar> ejemplares = controlador.getServicioEjemplar().findByFkPlanta(codigoPlanta);

                for (Ejemplar ejemplar : ejemplares){
                    int numMensajes = controlador.getServicioMensaje().findByEjemplar(ejemplar.getId()).size();
                    List<Mensaje> mensajes = controlador.getServicioMensaje().findByEjemplar(ejemplar.getId());

                    LocalDateTime localDateTime = null;
                    if (!mensajes.isEmpty()){
                        localDateTime = mensajes.get(mensajes.size()-1).getFechaHora();
                        System.out.println("Nombre del ejemplar" + ejemplar.getNombre() + " | Nº de mensajes: " + numMensajes + " | Fecha y Hora ultima actualizacion: " + localDateTime);
                    } else {
                        System.out.println("Nombre del ejemplar" + ejemplar.getNombre() + " | No hay mensajes todavia");
                    }
                }
            }
            System.out.println("----------------------------------------------------------------------");

        }
    }

    public void listAllMensajeByEjemplarID() {
        List<Ejemplar> ejemplares = controlador.getServicioEjemplar().findAll();

        if (!ejemplares.isEmpty()) {
            System.out.println("----------------------------------------------------------------------");
            for (Ejemplar ejemplar : ejemplares) {
                System.out.println(ejemplar.toString());
            }
            System.out.println("----------------------------------------------------------------------");

            System.out.println("¿De qué ejemplar quieres ver los mensajes? (Introduce el ID del ejemplar)");
            Long id = null;
            do {
                try {
                    id = sc.nextLong();
                } catch (InputMismatchException inputMismatchException) {
                    System.err.println("El ID introducido no es un número.");
                    sc.next();
                }

                if (id != null) {
                    List<Mensaje> mensajes = controlador.getServicioMensaje().findByEjemplar(id);
                    mensajes.sort(Comparator.comparing(Mensaje::getFechaHora));

                    for (Mensaje mensaje : mensajes) {
                        Persona persona = controlador.getServicioPersona().findById(mensaje.getPersona().getId());
                        System.out.println("Fecha y Hora: " + mensaje.getFechaHora() + " | Mensaje: " + mensaje.getMensaje() + " | Creado por: " + persona.getNombre());
                    }
                }
            } while (id == null || controlador.getServicioEjemplar().findById(id) == null);
        } else {
            System.err.println("No hay ejemplares registrados en el sistema.");
        }
    }


    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }
}
