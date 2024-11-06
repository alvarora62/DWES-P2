package org.dwes.util;

public class Validacion {

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


}
