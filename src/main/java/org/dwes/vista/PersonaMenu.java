package org.dwes.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonaMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    public PersonaMenu() {
    }

    public void menuPersona(){
        do {
            System.out.println("**Sistema Gestor del Viviero** (Gestión de Personal)");
            System.out.println("1 - Alta de un empleado");
            System.out.println("9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        // Alta Empleados
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
