package org.dwes.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    public MainMenu() {
    }

    public void menuPrincipal(){
        do {
            System.out.println("**Sistema Gestor del Viviero**");
            System.out.println("1 - Ver plantas");
            System.out.println("2 - Iniciar sesión");
            System.out.println("9 - Salir");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        // ver plantas
                        break;
                    case 2:
                        spacer();
                        // inicio sesion
                        break;
                    case 9:
                        spacer();
                        System.out.println("Apagando aplicación.");
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
