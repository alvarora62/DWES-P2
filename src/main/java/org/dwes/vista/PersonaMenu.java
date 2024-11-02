package org.dwes.vista;

import org.dwes.modelo.Persona;
import org.dwes.servicio.ServicioPersonaImpl;
import org.dwes.servicio.ServicioPlantaImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonaMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private ServicioPersonaImpl servicioPersona;

    public PersonaMenu() {
        servicioPersona = ServicioPersonaImpl.getServicioPersona();
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
                        System.out.println("Alta de un nuevo empleado");
                        Persona persona = new Persona();

                        System.out.println("Introduce el nombre del nuevo empleado:");
                        String nombre = sc.next();
                        persona.setNombre(nombre);
                        System.out.println("Introduce el email del nuevo empleado:");
                        String email = sc.next();
                        persona.setEmail(email);
                        if (!servicioPersona.save(persona)){
                            System.out.println("Error al guardar el empleado.");
                        }

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
