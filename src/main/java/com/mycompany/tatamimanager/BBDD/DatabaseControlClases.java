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
/**
 *
 * @author diana
 */
public class DatabaseControlClases {
    
     
    public static void guardarClase(String nombreClase, String horario, String dias, int idColegio, int idProfesor){
       
       try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "INSERT INTO clases (nombre, horario, dias, id_colegio, id_profesor) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreClase);
                st.setString(2, horario);
                st.setString(3, dias);
                st.setInt(4, idColegio);
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
  
    public static void editarClase(String nombre, String horario, String diasSeleccionados, int idColegio, int idProfesor, int id){ 
        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "UPDATE clases SET nombre = ?, horario = ?, dias = ?, id_colegio = ?, id_profesor = ?"
                    + "WHERE id_clase = ?";
            PreparedStatement st = conn.prepareStatement(query);

            // Asignar los valores de los campos de texto a la query
            st.setString(1, nombre);
            st.setString(2, horario);
            st.setString(3, diasSeleccionados);
            st.setInt(4, idColegio);
            st.setInt(5, idProfesor);
            st.setInt(6, id); // id es el identificador de la clase que se está editando

            // Ejecutar la query
            int rowsUpdated = st.executeUpdate();

            // Verificar si se realizó el cambio
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null , "La clase ha sido actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null , "No se pudo actualizar la clase.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null , "No se pudo actualizar la clase.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(AddClase.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void eliminarClase (int idClase) {
        try (Connection conn = DatabaseManager.getConnection()) {
            // Sentencia SQL para eliminar
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM clases WHERE id_clase = ?"
            );
            st.setInt(1, idClase);
            int filasEliminadas = st.executeUpdate();
            System.out.println("id_clase ="+idClase);

            // Verificar si se eliminó algo
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "La clase ha sido eliminada correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No se realizó ninguna eliminación.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
}
