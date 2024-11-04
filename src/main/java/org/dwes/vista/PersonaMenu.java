package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;
import org.dwes.servicio.ServicioCredencialesImpl;
import org.dwes.servicio.ServicioPersonaImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonaMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;

    public PersonaMenu() {
        this.controlador = Controlador.getControlador();
    }

    public void menuPersona(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** (Gestión de Personal)");
            System.out.println("\t\t\t1 - Alta de un empleado");
            System.out.println("\t\t\t9 - Atrás");

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
                            System.out.print("\tDatos personales del usuario.");
                            System.out.println("\tIntroduce el nombre del nuevo empleado:");
                            String nombre = sc.next();
                            System.out.println("\tIntroduce el email del nuevo empleado: \nPatron que ha seguir x@x.(com/es/org).\nEl email no puede existir ya en el sistema.");
                            String email = sc.next();

                            persona.setNombre(nombre);
                            persona.setEmail(email);
                            if (controlador.getServicioPersona().save(persona)){
                                guardado = true;
                            } else {
                                System.err.println("Email con formato invalido o repetido.");
                            }
                        }while (!guardado);
                        guardado = false;
                        spacer();


                        // Creacion del objeto credenciales
                        do{
                            System.out.println("\tDatos de acceso para el usuario.");
                            System.out.println("\tUsuaio del empleado: \nDebe de ser único.)");
                            String usuario = sc.next();
                            System.out.println("\tContraseña del empleado: \nAl menos 8 caracteres.\nIncluir como mínimo una mayuscula, un símbolo y un número.");
                            String password = sc.next();

                            credenciales.setUsuario(usuario);
                            credenciales.setPassword(password);
                            credenciales.setFk_persona(controlador.getServicioPersona().findByEmail(persona.getEmail()));
                            if (controlador.getServicioCredenciales().save(credenciales)){
                                guardado = true;
                            } else {
                                System.err.println("Usuario ya existe. O contraseña no válida.");
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
