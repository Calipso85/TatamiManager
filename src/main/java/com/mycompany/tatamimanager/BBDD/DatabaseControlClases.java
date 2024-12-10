/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.tatamimanager.Clases.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 *
 * @author diana
 */
public class DatabaseControlClases {
    
     
    public static void guardarClase(String nombreClase, String horario, String dias, int idClase, int idProfesor){
       
       try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "INSERT INTO clases (nombre, horario, dias, id_colegio, id_profesor) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreClase);
                st.setString(2, horario);
                st.setString(3, dias);
                st.setInt(4, idClase);
                st.setInt(5, idProfesor);

                // Ejecutar el INSERT
                int filasInsertadas = st.executeUpdate();
                if (filasInsertadas > 0) {
                    Logger.getLogger(AddClase.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla clases");
                } else {
                    Logger.getLogger(AddClase.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla clases.");
                }
            }

            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd

        } catch (SQLException e) {
            Logger.getLogger(AddClase.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(AddClase.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    
    
    
    
}
