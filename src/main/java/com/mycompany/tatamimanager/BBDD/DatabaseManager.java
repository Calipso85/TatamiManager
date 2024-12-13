/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.BBDD;


import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diana
 */
public class DatabaseManager {
    private static Connection conn;

    // Método para obtener la conexión
    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            // Cargar la base de datos desde el classpath
            //La bbdd está en TatamiManager\target\classes\database\club_judo.db
            URL resourceUrl = DatabaseManager.class.getClassLoader().getResource("database/club_judo.db");
            if (resourceUrl == null) {
                throw new IllegalStateException("No se encontró la base de datos");
            }
            String dbPath = Paths.get(resourceUrl.toURI()).toAbsolutePath().toString();
            String url = "jdbc:sqlite:" + dbPath;

            // Crear la conexión
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection created successfully");
        }
        return conn;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                //System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}