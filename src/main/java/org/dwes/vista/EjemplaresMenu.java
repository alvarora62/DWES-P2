package org.dwes.vista;

import org.dwes.controlador.Controlador;

import java.util.Scanner;

public class EjemplaresMenu {

    boolean on = true;
    Scanner sc = new Scanner(System.in);

    private final Controlador controlador;

    public EjemplaresMenu() {
        this.controlador = Controlador.getControlador();
    }

    public void menu(){

    }
}
