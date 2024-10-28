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

    private static Connexion connexion;
    private final MysqlDataSource mysqlDataSource;

    private Connexion() {
        mysqlDataSource = new MysqlDataSource();
        loadProperties();
    }
    
    public static Connexion getConnexion() {
        if (connexion == null) {
            connexion = new Connexion();
        }
        return connexion;
    }

    /**
     * Carga las propiedades de conexión desde el archivo 'db.properties'.
     * Establece la URL, el 'user' y la 'passwd' en el datasource.
     * Si ocurre un error al cargar las propiedades, se imprime un mensaje
     * de error en la consola.
     */
    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(fis);
            mysqlDataSource.setUrl(properties.getProperty("url"));
            mysqlDataSource.setUser(properties.getProperty("user"));
            mysqlDataSource.setPassword(properties.getProperty("passwd"));
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return un objeto Connection si no falla;
     *         si falla, devuelve null y se imprime un mensaje de error.
     */
    public Connection getConexion() {
        try {
            return mysqlDataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
