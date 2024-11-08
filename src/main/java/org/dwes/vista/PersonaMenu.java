package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Credenciales;
import org.dwes.modelo.Persona;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.dwes.vista.MainMenu.activeUser_username;

public class PersonaMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;

    public PersonaMenu() {
        this.controlador = Controlador.getControlador();
    }

    public void menuPersona(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** (Gestión de Personal) [Usuario activo: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Alta de un empleado");
            System.out.println("\t\t\t9 - Atrás");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        Persona persona = savePersona();
                        saveCredenciales(persona);
                        spacer();

                        System.out.println("Empleado y credenciales de acceso creadas con exito.");
                        break;
                    case 9:
                        spacer();
                        System.out.println("Saliendo de la gestión de empleados...");
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

    private Persona savePersona() {
        Persona persona = new Persona();
        System.out.println("Alta de un nuevo empleado. ");
        System.out.print("\n\nDatos personales del usuario.");

        // Validacion nombre
        String name;
        do {
            System.out.println("\nIntroduce el nombre del nuevo empleado:");
            name = sc.next();
            if (controlador.getServicioPersona().checkName(name))
                System.err.println("Error en el formato del nombre");
        }while (controlador.getServicioPersona().checkName(name));

        String email;
        boolean isValidEmail;
        do {
            System.out.println("\nIntroduce el email del nuevo empleado: \nSe tendrá en cuenta el siguiente patrón: x@x.(com/es/org). Además, el email no puede existir ya en el sistema.\n");

            email = sc.next();
            isValidEmail = controlador.getServicioPersona().checkEmail(email);

            if (!isValidEmail) {
                System.err.println("Error en el formato del email o email ya existente en el sistema.");
            }
        } while (!isValidEmail);

        // Guardado de la persona
        persona.setNombre(name);
        persona.setEmail(email);
        if (!controlador.getServicioPersona().save(persona)){
            System.err.println("Error guardando la persona.");
        }

        return persona;
    }

    private void saveCredenciales(Persona persona) {
        Credenciales credenciales = new Credenciales();
        String username;
        String passwd;

        System.out.println("\n\nCreacion de datos de acceso para el usuario nuevo.");

        // Validacion username
        do {
            System.out.println("Ingrese un nombre de usuario único:");
            username = sc.next();

            if (!controlador.getServicioCredenciales().chechUsername(username)) {
                System.err.println("El nombre de usuario ya está en uso. Intente con otro.");
            }
        } while (!controlador.getServicioCredenciales().chechUsername(username));

        // Validacion passwd
        do {
            System.out.println("Ingrese una contraseña para el empleado (mínimo 8 caracteres, al menos una mayúscula, un símbolo y un número):");
            passwd = sc.next();

            if (!controlador.getServicioCredenciales().checkPassword(passwd)) {
                System.err.println("Contraseña no válida. Intente nuevamente.");
            }
        } while (!controlador.getServicioCredenciales().checkPassword(passwd));

        credenciales.setUsuario(username);
        credenciales.setPassword(passwd);

        // Creacion del objeto Persona a relacionar con Credenciales
        Persona personaData = controlador.getServicioPersona().findByEmail(persona.getEmail());
        if (personaData != null) {
            credenciales.setFk_persona(personaData);
        } else {
            System.err.println("No se pudo encontrar la persona para asignar las credenciales.");
            return;
        }

        // Guardar credenciales
        if (!controlador.getServicioCredenciales().save(credenciales)) {
            System.err.println("Error al guardar las credenciales.");
        } else {
            System.out.println("Credenciales guardadas con éxito para el usuario: " + username);
        }
    }

    private void spacer(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }
}
