package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Credenciales;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private final PlantasMenu plantasMenu;
    private final PersonaMenu personaMenu;
    private final EjemplaresMenu ejemplaresMenu;
    private final MensajesMenu mensajesMenu;
    private final Controlador controlador;

    static Long activeUser_id;
    static String activeUser_username;
    boolean on = true;
    Scanner sc = new Scanner(System.in);

    public MainMenu() {
        plantasMenu = new PlantasMenu();
        personaMenu = new PersonaMenu();
        ejemplaresMenu = new EjemplaresMenu();
        mensajesMenu = new MensajesMenu();
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
                        activeUser_username = sc.next();
                        System.out.println("\tContraseña:");
                        String password = sc.next();

                        nivel = controlador.getServicioCredenciales().login(activeUser_username, password);

                        switch (nivel) {
                            case -1:
                                System.out.println("Error en el usuario o la contraseña.");
                                break;
                            case 0:
                                Credenciales login = controlador.getServicioCredenciales().findByUsername(activeUser_username);
                                activeUser_id = controlador.getServicioPersona().findById(login.getFk_persona().getId()).getId();
                                password = "";
                                menuPrincipalPersonal();
                                on = true;
                                break;
                            case 1:
                                activeUser_id = controlador.getServicioPersona().findByEmail("admin@admin.com").getId();
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
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario Actual: " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Gestión ejemplares");
            System.out.println("\t\t\t2 - Gestión de mensajes");
            System.out.println("\t\t\t9 - Cerrar Sesión");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        ejemplaresMenu.menuEjemplaresUser();
                        on = true;
                        break;
                    case 2:
                        spacer();
                        mensajesMenu.menuMensajesUser();
                        on = true;
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
            System.out.println("\t\t\t**Sistema Gestor del Viviero** [Usuario Actual " + activeUser_username + "]");
            System.out.println("\t\t\t1 - Gestion de plantas");
            System.out.println("\t\t\t2 - Gestión ejemplares");
            System.out.println("\t\t\t3 - Gestion de empleados");
            System.out.println("\t\t\t4 - Gestión de mensajes");
            System.out.println("\t\t\t9 - Cerrar Sesion");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        plantasMenu.menuPlantas();
                        on = true;
                        break;
                    case 2:
                        spacer();
                        ejemplaresMenu.menuEjemplaresUser();
                        on = true;
                        break;
                    case 3:
                        spacer();
                        personaMenu.menuPersona();
                        on = true;
                        break;
                    case 4:
                        spacer();
                        mensajesMenu.menuMensajesUser();
                        on = true;
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
