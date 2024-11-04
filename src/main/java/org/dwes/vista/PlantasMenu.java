package org.dwes.vista;

import org.dwes.modelo.Planta;
import org.dwes.servicio.ServicioPlantaImpl;
import org.dwes.repositorio.PlantaDAOImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlantasMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private ServicioPlantaImpl servicioPlanta;

    public PlantasMenu() {
        servicioPlanta = ServicioPlantaImpl.getServicioPlanta();
    }

    /**
     * Vista para el usuario invitado, automaticamente al llegar aqui muestra el listado de plantas.
     */
    public void vistaInvitado(){
        listadoPlantas();
    }


    public void menuPlantas(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** (Gestión de Personal)");
            System.out.println("\t\t\t1 - Registrar planta (NO IMPLEMENTADO)");
            System.out.println("\t\t\t2 - Listar plantas");
            System.out.println("\t\t\t3 - Modificar planta (NO IMPLEMENTADO)");
            System.out.println("\t\t\t9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        boolean guardado = false;

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

                            if (servicioPlanta.save(planta)){
                                spacer();
                                System.out.println("Planta registrada correctamente");
                                guardado = true;
                            } else {
                                System.err.println("Error de formato en la planta introducida.");
                            }

                        }while (!guardado);
                        break;
                    case 2:
                        spacer();
                        listadoPlantas();
                        break;
                    case 3:
                        spacer();
                        // Modificar planta
                        break;
                    case 4:
                        spacer();
                        // Eliminar planta
                        break;
                    case 9:
                        spacer();
                        on = false;
                        break;
                    default:
                        spacer();
                        System.err.println("Opción no válida. Por favor, introduzca alguna opción válida de las presentadas.");
                        break;
                }
            } catch (InputMismatchException e){
                spacer();
                System.err.println("Dato introducido no válido. Por favor, introduce una opcion valida." + e.getMessage());
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
        List<Planta> plantas = servicioPlanta.listarPlantas();

        if (plantas.isEmpty()){
            System.out.println("No hay plantas registradas en el sistema.");
        } else {
            System.out.println("Listado de plantas");
            System.out.println("");
            for (Planta planta : plantas) {
                System.out.println(planta.toString());
            };
        }
    }
}
