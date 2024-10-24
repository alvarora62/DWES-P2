package org.dwes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    Connection con;

    public Connexion(){
        try{
            //con = DriverManager.getConnection(url,user,passwd);
        } catch (SQLException e) {
            System.err.println("No has sido posible iniciar crear la conexion con la base de datos --> " + e.getMessage());
        }
    }
}
