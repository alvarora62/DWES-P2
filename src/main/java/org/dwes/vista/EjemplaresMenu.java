package org.dwes.vista;

import org.dwes.controlador.Controlador;
import org.dwes.modelo.Ejemplar;
import org.dwes.modelo.Mensaje;
import org.dwes.modelo.Planta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EjemplaresMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;
    private final PlantasMenu plantasMenu;

    public EjemplaresMenu() {
        this.controlador = Controlador.getControlador();
        this.plantasMenu = new PlantasMenu();
    }

    /**
     * Menu presentado a un perfil de usuario
     */
    public void menuEjemplaresUser(){
        do {
            System.out.println("\t\t\t**Sistema Gestor del Viviero**  [Usuario Activo: " + MainMenu.username + "]");
            System.out.println("\t\t\t1 - Resgistrar ejemplar (NO IMPLEMENTADO)");
            System.out.println("\t\t\t2 - Listar ejemplares por Planta (NO IMPLEMENTADO)");
            System.out.println("\t\t\t3 - Ver mensajes de seguimiento (NO IMPLEMENTADO)");
            System.out.println("\t\t\t9 - Cerrar Sesion");

            try{
                int answer = sc.nextInt();

                switch (answer) {
                    case 1:
                        spacer();
                        System.out.println("Registro de un nuevo ejemplar.\n");
                        plantasMenu.listadoPlantas();

                        boolean repetir = true;
                        Planta planta;

                        do {
                            System.out.println("¿De qué tipo de planta es el ejemplar nuevo?");
                            String fk_planta = sc.next().toUpperCase();
                            planta = controlador.getServicioPlanta().findByCodigo(fk_planta);
                            if (planta != null){
                                repetir = false;
                            }
                        }while (repetir);

                        Ejemplar ejemplar = new Ejemplar();
                        ejemplar.setPlanta(planta);
                        if (controlador.getServicioEjemplar().save(ejemplar)){
                            Mensaje mensaje = new Mensaje();
                            mensaje.setPersona(controlador.getServicioPersona().findById(MainMenu.activeUser));
                            controlador.getServicioMensaje().mensajeInicial(mensaje);
                        }

                        System.out.println("Exito");
                        break;
                    case 2:
                        spacer();
                        controlador.getServicioEjemplar().findByFkPlanta(1L);
                        break;
                    case 3:
                        spacer();
                        controlador.getServicioMensaje().findByEjemplar(1L);
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
