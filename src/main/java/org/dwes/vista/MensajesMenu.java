package org.dwes.vista;

import org.dwes.controlador.Controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

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
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario activo: " + MainMenu.username + "]");
            System.out.println("\t\t\t1 - Resgistrar ejemplar (NO IMPLEMENTADO)");
            System.out.println("\t\t\t2 - Listar ejemplares por Planta (NO IMPLEMENTADO)");
            System.out.println("\t\t\t3 - Ver mensajes de seguimiento (NO IMPLEMENTADO)");
            System.out.println("\t\t\t9 - Cerrar Sesion");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();

                        break;
                    case 2:
                        spacer();

                        break;
                    case 3:
                        spacer();
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

    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }
}
