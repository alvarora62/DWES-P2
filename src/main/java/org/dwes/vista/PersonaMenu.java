package org.dwes.vista;

import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;
import org.dwes.servicio.ServicioCredencialesImpl;
import org.dwes.servicio.ServicioPersonaImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonaMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private ServicioPersonaImpl servicioPersona;
    private ServicioCredencialesImpl servicioCredenciales;

    public PersonaMenu() {
        servicioPersona = ServicioPersonaImpl.getServicioPersona();
        servicioCredenciales = ServicioCredencialesImpl.getServicioCredenciales();
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
                        Persona persona = new Persona();
                        Credenciales credenciales = new Credenciales();
                        System.out.println("Alta de un nuevo empleado. ");
                        boolean guardado = false;

                        // Creacion del objeto persona
                        do {
                            System.out.print(" Datos personales del usuario.");
                            System.out.println("Introduce el nombre del nuevo empleado:");
                            String nombre = sc.next();
                            System.out.println("Introduce el email del nuevo empleado: (el patron ha seguir debe ser x@x.(com/es/org, además el email no puede existir ya en el sistema)");
                            String email = sc.next();

                            persona.setNombre(nombre);
                            persona.setEmail(email);
                            if (servicioPersona.save(persona)){
                                guardado = true;
                            } else {
                                System.err.println("Email con formato invalido o repetido.");
                            }
                        }while (!guardado);
                        guardado = false;
                        spacer();


                        // Creacion del objeto credenciales
                        do{
                            System.out.println("Datos de acceso para el usuario.");
                            System.out.println("Usuaio del empleado: (el nombre de usuario debe de ser único)");
                            String usuario = sc.next();
                            System.out.println("Contraseña del empleado: (tienes que ser de almenos 8 caracteres, incluir una mayuscula, un simbolo y un numero como minimo.");
                            String password = sc.next();

                            credenciales.setUsuario(usuario);
                            credenciales.setPassword(password);
                            credenciales.setFk_persona(servicioPersona.findByEmail(persona.getEmail()));
                            if (servicioCredenciales.save(credenciales)){
                                guardado = true;
                            } else {
                                System.err.println("Usuario ya existe.");
                            }
                        }while (!guardado);
                        spacer();

                        System.out.println("Empleado y credenciales de acceso creadas con exito.");
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
