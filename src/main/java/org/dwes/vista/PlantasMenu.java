package org.dwes.vista;

import org.dwes.servicio.ServicioPlantaImpl;
import org.dwes.repositorio.PlantaDAOImpl;

import java.util.InputMismatchException;
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
        servicioPlanta.listarPlantas();
    }


    public void menuPlantas(){
        do {
            System.out.println("**Sistema Gestor del Viviero** (Gestión de Personal)");
            System.out.println("1 - Registrar planta (NO IMPLEMENTADO)");
            System.out.println("2 - Listar plantas");
            System.out.println("3 - Modificar planta (NO IMPLEMENTADO)");
            System.out.println("4 - Eliminar planta (NO IMPLEMENTADO)");
            System.out.println("9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        // Registrar planta
                        break;
                    case 2:
                        spacer();
                        servicioPlanta.listarPlantas();
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
}
