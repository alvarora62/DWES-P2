package org.dwes.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

    private static final MysqlDataSource mysqlDataSource = new MysqlDataSource();
    private static final Properties prop = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            prop.load(fis);
            mysqlDataSource.setUrl(prop.getProperty("url"));
            mysqlDataSource.setUser(prop.getProperty("usuario"));
            mysqlDataSource.setPassword(prop.getProperty("password"));
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        try {
            return mysqlDataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("SQLException occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}