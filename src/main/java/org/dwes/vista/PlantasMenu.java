package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Planta;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.dwes.vista.MainMenu.activeUser_username;

public class PlantasMenu {

    boolean on = true;
    boolean estado = false;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;

    public PlantasMenu() {
        controlador = Controlador.getControlador();
    }

    /**
     * Vista del menu de plantas para un perfil de administrador
     */
    public void menuPlantas(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** (Gestión de Plantas) [Usuario activo: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Registrar planta");
            System.out.println("\t\t\t2 - Listar plantas");
            System.out.println("\t\t\t3 - Modificar planta");
            System.out.println("\t\t\t9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        estado = false;

                        System.out.println("Registro de una nueva planta.");
                        System.out.println("");

                        do {
                            System.out.println("\tCódigo de la planta: (Deberá de ser entero en mayusculas)");
                            String codigo = sc.next();
                            System.out.println("\tNombre comun de la planta:");
                            String nombreComun = sc.next();
                            System.out.println("\tNombre cientifico de la planta");
                            String nombreCientifico = sc.next();

                            Planta planta = new Planta(codigo,nombreComun,nombreCientifico);

                            if (controlador.getServicioPlanta().save(planta)){
                                spacer();
                                System.out.println("Planta registrada correctamente");
                                estado = true;
                            } else {
                                System.err.println("Error de formato en la planta introducida.");
                            }
                        }while (!estado);
                        estado = false;
                        break;
                    case 2:
                        spacer();
                        listadoPlantas();
                        break;
                    case 3:
                        spacer();
                        estado = false;

                        System.out.println("Actualizar una planta existente.\n");
                        String codigo;
                        listadoPlantas();

                        List<Planta> plantaList = controlador.getServicioPlanta().listarPlantas();
                        if (!plantaList.isEmpty()){
                            do {
                                System.out.println("¿Qué planta quieres actualizar?");
                                System.out.println("CODIGO:");
                                codigo = sc.next();

                                Planta findByCodigo = controlador.getServicioPlanta().findByCodigo(codigo);
                                if (findByCodigo != null){
                                    estado = true;
                                    System.out.println("Planta valida para la actualizacion de datos.");
                                } else {
                                    System.out.println("Planta no encontrada, intenta otro codigo.");
                                }
                            }while (!estado);
                            estado = false;

                            do {
                                System.out.println("\tNuevo nombre comun para la planta:");
                                String nombreComun = sc.next();
                                System.out.println("\tNuevo nombre cientifico para la planta");
                                String nombreCientifico = sc.next();

                                Planta planta = new Planta();
                                planta.setCodigo(codigo);
                                planta.setNombreComun(nombreComun);
                                planta.setNombreCientifico(nombreCientifico);

                                if (controlador.getServicioPlanta().update(planta)){
                                    spacer();
                                    System.out.println("Planta actualizada correctamente");
                                    estado = true;
                                } else {
                                    System.err.println("Error de formato en la planta introducida.");
                                }

                            }while (!estado);
                            estado = false;
                        } else {
                            System.err.println("No hay plantas registradas en el sistema.");
                        }
                        break;
                    case 4:
                        spacer();
                        // Eliminar planta
                        break;
                    case 9:
                        spacer();
                        System.out.println("Saliendo de la gestion de plantas.");
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

    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }


    /**
     * Metodo que muestra por pantalla todas las plantas registradas en el sistema.
     * Si no hay ninguna mostrara un mensaje diciendolo.
     */
    public void listadoPlantas(){
        List<Planta> plantas = controlador.getServicioPlanta().listarPlantas();

        if (plantas.isEmpty()){
            System.out.println("No hay plantas registradas en el sistema.");
        } else {
            System.out.println("Listado de plantas");
            System.out.println("");
            System.out.println("--------------------------------------------------------------------");
            for (Planta planta : plantas) {
                System.out.println(planta.toString());
            };
            System.out.println("--------------------------------------------------------------------");
        }
    }
}
