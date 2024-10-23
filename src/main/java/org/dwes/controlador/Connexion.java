package org.dwes.controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

public class Connexion {

    Connection con;
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    Properties prop = null;
    FileInputStream fis;

    String url;
    String user;
    String passwd;

    try{
        fis = new FileInputStream("src/main/resources/db.properties");
        prop.load(fis);
        url = prop.getProperty("url");
        user = prop.getProperty("usuario");
        password = prop.getProperty("password");
        m.setUrl(url);
        m.setUser(usuario);
        m.setPassword(password);

        con = m.getConnection();

        String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES ('"+nueva.getCodigo()+"' , '"+nueva.getNombrecomun()+"', '"+nueva.getNombrecientifico()+"')";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.execute();

        con.close();

    } catch (SQLException e) {
        System.out.println("Se ha producido una SQLException : " +e.getLocalizedMessage());
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        System.out.println("Se ha producido una FileNotFoundException : " +e.getLocalizedMessage());			e.printStackTrace();
    } catch (IOException e) {
        System.out.println("Se ha producido una IOException : " +e.getLocalizedMessage());
        e.printStackTrace();
    }
}
