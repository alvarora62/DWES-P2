package org.dwes.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;

public class Validacion {

    static Scanner sc = new Scanner(System.in);

    /**
     * Metodo creado para transformar un String en un booleano y validar la entrada
     *
     * @param string valor introducido
     * @return -1 si el valor introducido es invalido, 0 si es un valor valido, 1 si no es un valor valido
     */
    public static int yesNoQuestion(String string){
        if (string.equalsIgnoreCase("s")){
            return 0;
        } else if (string.equalsIgnoreCase("n")) {
            return 1;
        } else {
            return -1;
        }
    }

    public static LocalDateTime pedirFecha() {

        // Crear datos para la fecha
        System.out.println("Dime un día:");
        String dia_String = sc.next();
        System.out.println("Dime un mes:");
        String mes_String = sc.next();
        System.out.println("Dime un año:");
        String ano_String = sc.next();

        int dia = 0;
        int mes = 0;
        int ano = 0;
        try {
            dia = Integer.parseInt(dia_String);
            mes = Integer.parseInt(mes_String);
            ano = Integer.parseInt(ano_String);
        } catch (NumberFormatException e) {
            System.err.println("Valor introducido NO válido: " + e.getMessage());
            return null;
        }

        // Validar fecha
        LocalDateTime userDate;
        try {
            userDate = LocalDateTime.of(ano, mes, dia, 0, 0);
        } catch (Exception e) {
            System.err.println("Fecha no válida: " + e.getMessage());
            return null;
        }

        // Mirar si la fecha actual es menor que la introducida
        LocalDateTime currentDate = LocalDateTime.now();
        if (userDate.compareTo(currentDate) > 0) {
            System.err.println("La fecha es futura. Fecha inválida.");
            return null;
        }

        return userDate;
    }


}
