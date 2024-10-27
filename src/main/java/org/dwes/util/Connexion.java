package org.dwes.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase para gestionar la conexión a una base de datos MySQL utilizando un
 * datasource. Esta clase carga las propiedades de conexión desde un archivo
 * .properties y proporciona un método para obtener una conexión.
 */

public class Connexion {

    private static final MysqlDataSource MYSQL_DATA_SOURCE = new MysqlDataSource();
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    /**
     * Carga las propiedades de conexión desde el archivo 'db.properties'.
     * Establece la URL, el 'user' y la 'passwd' en el datasource.
     *
     * Si ocurre un error al cargar las propiedades, se imprime un mensaje
     * de error en la consola.
     */

    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            PROPERTIES.load(fis);
            MYSQL_DATA_SOURCE.setUrl(PROPERTIES.getProperty("url"));
            MYSQL_DATA_SOURCE.setUser(PROPERTIES.getProperty("user"));
            MYSQL_DATA_SOURCE.setPassword(PROPERTIES.getProperty("passwd"));
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            System.err.println("Unexpected error ocurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return un Objeto Connection si no falla;
     *         si falla, devuelve null y se imprime un mensaje de error.
     */
    public static Connection getConexion() {
        try {
            return MYSQL_DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            System.err.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}