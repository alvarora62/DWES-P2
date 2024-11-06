package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Credenciales;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private final PlantasMenu plantasMenu;
    private final PersonaMenu personaMenu;
    private final EjemplaresMenu ejemplaresMenu;
    private final Controlador controlador;

    static String username;
    static Long activeUser;
    boolean on = true;
    Scanner sc = new Scanner(System.in);

    public MainMenu() {
        plantasMenu = new PlantasMenu();
        personaMenu = new PersonaMenu();
        ejemplaresMenu = new EjemplaresMenu();
        this.controlador = Controlador.getControlador();
    }

    /**
     * Menu presentado al perfil de invitado (al abrir la aplicación).
     */
    public void menuPrincipal(){
        Credenciales credenciales = controlador.getServicioPersona().checkForAdmin();
        if (credenciales != null)
            controlador.getServicioCredenciales().save(credenciales);

        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero**");
            System.out.println("\t\t\t1 - Ver plantas");
            System.out.println("\t\t\t2 - Iniciar sesión");
            System.out.println("\t\t\t9 - Salir de la aplicación");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        plantasMenu.listadoPlantas();
                        on = true;
                        break;
                    case 2:
                        spacer();
                        int nivel;

                        System.out.println("\tNombre de usuario");
                        username = sc.next();
                        System.out.println("\tContraseña:");
                        String password = sc.next();

                        nivel = controlador.getServicioCredenciales().login(username, password);

                        switch (nivel) {
                            case -1:
                                System.out.println("Error en el usuario o la contraseña.");
                                break;
                            case 0:
                                menuPrincipalPersonal();
                                Credenciales login = controlador.getServicioCredenciales().findByUsername(username);
                                activeUser = login.getId();
                                password = "";
                                on = true;
                                break;
                            case 1:
                                menuPrincipalAdmin();
                                activeUser = 0L;
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
                System.err.println("Dato introducido no válido. Por favor, introduce una opcion valida.");
                sc.next();
            }
        } while (on);
    }

    /**
     * Menu presentado a un empleado del vivero
     */
    public void menuPrincipalPersonal(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario Actual: " + username + "]");
            System.out.println("\t\t\t1 - Gestión ejemplares");
            System.out.println("\t\t\t9 - Cerrar Sesión");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        ejemplaresMenu.menuEjemplaresUser();
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

    /**
     * Menu presentado a un perfil de administrador
     */
    public void menuPrincipalAdmin(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario Actual " + username + "]");
            System.out.println("\t\t\t1 - Gestion de plantas");
            System.out.println("\t\t\t2 - Gestión ejemplares");
            System.out.println("\t\t\t3 - Gestion de empleados");
            System.out.println("\t\t\t9 - Cerrar Sesion");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        plantasMenu.menuPlantas();
                        break;
                    case 2:
                        spacer();
                        ejemplaresMenu.menuEjemplaresUser();
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
