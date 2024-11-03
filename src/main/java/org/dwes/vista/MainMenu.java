package org.dwes.vista;

import org.dwes.servicio.ServicioCredencialesImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private final PlantasMenu plantasMenu;
    private final PersonaMenu personaMenu;
    private final ServicioCredencialesImpl servicioCredenciales;

    private String username;
    private String password;
    boolean on = true;
    Scanner sc = new Scanner(System.in);

    public MainMenu() {
        plantasMenu = new PlantasMenu();
        personaMenu = new PersonaMenu();
        servicioCredenciales = ServicioCredencialesImpl.getServicioCredenciales();
    }

    public void menuPrincipal(){
        do {
            System.out.println("**Sistema Gestor del Viviero**");
            System.out.println("1 - Entrar como invitado");
            System.out.println("2 - Iniciar sesión");
            System.out.println("9 - Salir de la aplicación");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        menuPrincipalinvitado();
                        on = true;
                        break;
                    case 2:
                        spacer();
                        int nivel;

                        System.out.println("Nombre de usuario");
                        username = sc.next();
                        System.out.println("Contraseña:");
                        password = sc.next();

                        nivel = servicioCredenciales.login(username,password);

                        switch (nivel) {
                            case -1:
                                System.out.println("Error en el usuario o la contraseña.");
                                break;
                            case 0:
                                menuPrincipalPersonal();
                                on = true;
                                break;
                            case 1:
                                menuPrincipalAdmin();
                                on = true;
                                break;
                        }

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

    /**
     * Menu presentado al perfil de invitado.
     */
    public void menuPrincipalinvitado(){
        do {
            System.out.println("**Sistema Gestor del Viviero**");
            System.out.println("1 - Ver plantas");
            System.out.println("9 - Volver");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        plantasMenu.vistaInvitado();
                        break;
                    case 9:
                        spacer();
                        System.out.println("Volviendo al menu principal.");
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

    /**
     * Menu presentado a un empleado del vivero
     */
    public void menuPrincipalPersonal(){
        do {
            System.out.println("**Sistema Gestor del Viviero** Usuario Actual: " + username);
            System.out.println("1 - Gestión ejemplares (NO IMPLEMENTADO)");
            System.out.println("9 - Cerrar Sesión");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        // Gestion Ejemplares
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
                System.err.println("Dato introducido no válido. Por favor, introduce una opcion valida." + e.getMessage());
                sc.next();
            }
        } while (on);
    }

    /**
     * Menu presentado a un perfil de administrador
     */
    public void menuPrincipalAdmin(){
        do {
            System.out.println("**Sistema Gestor del Viviero** Usuario Actual " + username);
            System.out.println("1 - Gestion de plantas (NO IMPLEMENTADO)");
            System.out.println("2 - Gestión ejemplares (NO IMPLEMENTADO)");
            System.out.println("3 - Gestion de empleados");
            System.out.println("9 - Salir de la aplicación");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        // gestion plantas
                        break;
                    case 2:
                        spacer();
                        // gestion ejemplares
                        break;
                    case 3:
                        spacer();
                        personaMenu.menuPersona();
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
